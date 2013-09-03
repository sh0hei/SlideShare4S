package com.taroid.slideshare4s

import scala.xml.Elem
import scala.xml.factory.XMLLoader

private class SlideShareImpl(
  val apiKey: String,
  val sharedSecret: String,
  private val xmlLoader: XMLLoader[Elem],
  private val xmlToSlideshows: XmlToSlideshows) extends SlideShare {

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

    xmlToSlideshows.toSlideshows(xmlLoader.load(url))
  }
}

private object SlideShareImpl {
  private object Urls {
    private val BASE = "https://www.slideshare.net/api/2/"
    val SEARCH = BASE + "search_slideshows"
  }
}