package com.taroid.slideshare4s

import scala.xml.Elem
import scala.xml.factory.XMLLoader

private class SlideShareImpl(
  val apiKey: String,
  val sharedSecret: String,
  private val xmlLoader: XMLLoader[Elem],
  private val xmlToSlideshows: XmlToSlideshows) extends SlideShare {

  if(apiKey == null) {
    throw new NullPointerException("apiKey must not be null.")
  }
  assert(apiKey.size > 0)

  if(sharedSecret == null) {
    throw new NullPointerException("sharedSecret must not be null.")
  }
  assert(sharedSecret.size > 0)

  override def searchSlideshows(query: Query, paging: Paging): Seq[Slideshow] = {
    if(query == null) {
      throw new NullPointerException("query must not be null.")
    }
    if(paging == null) {
      throw new NullPointerException("paging must not be null.")
    }

    import SlideShareImpl._

    val url = Utils.createUrlWithParams(Urls.SEARCH,
      "api_key" -> apiKey,
      "ts" -> currentTimeSeconds,
      "hash" -> hash,
      "q" -> query.words,
      "lang" -> query.language,
      "sort" -> query.sortOrder,
      "page" -> paging.page,
      "items_per_page" -> paging.itemsPerPage
    )

    xmlToSlideshows.convert(xmlLoader.load(url))
  }
}

private object SlideShareImpl {
  private object Urls {
    private val BASE = "https://www.slideshare.net/api/2/"
    val SEARCH = BASE + "search_slideshows"
  }
}