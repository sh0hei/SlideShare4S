package com.taroid.slideshare4s

import java.text.SimpleDateFormat
import scala.xml.XML

private class SlideShareImpl(val apiKey: String, val sharedSecret: String) extends SlideShare {
  assert(apiKey != null)
  assert(apiKey.size > 0)
  assert(sharedSecret != null)
  assert(sharedSecret.size > 0)

  override def searchSlideshow(query: Query): Seq[Slideshow] = {
    require(query != null)

    import SlideShareImpl._

    val url = Utils.createUrlWithParams(Urls.SEARCH,
      "api_key" -> apiKey,
      "ts" -> currentTimeSeconds,
      "hash" -> hash,
      "q" -> query.words,
      "page" -> query.page,
      "items_per_page" -> query.itemsPerPage,
      "lang" -> query.language,
      "sort" -> query.sortOrder
    )

    val dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy")

    return (XML.load(url) \ Tags.SLIDESHOW) map { e =>
      Slideshow(
        id = (e \ Tags.ID).text.toLong,
        title = (e \ Tags.TITLE).text,
        username = (e \ Tags.USERNAME).text,
        description = (e \ Tags.DESC).text,
        url = (e \ Tags.URL).text,
        thumbnailUrl = (e \ Tags.THUMB_URL).text,
        created = dateFormat.parse((e \ Tags.CREATED).text),
        updated = dateFormat.parse((e\ Tags.UPDATED).text),
        language = (e \ Tags.LANG).text
      )
    }
  }
}

private object SlideShareImpl {
  private object Urls {
    private val BASE = "https://www.slideshare.net/api/2/"
    val SEARCH = BASE + "search_slideshows"
  }

  private object Tags {
    val SLIDESHOW = "Slideshow"
    val ID = "ID"
    val TITLE = "Title"
    val USERNAME = "Username"
    val DESC = "Description"
    val URL = "URL"
    val THUMB_URL = "ThumbnailURL"
    val CREATED = "Created"
    val UPDATED = "Updated"
    val LANG = "Language"
  }
}