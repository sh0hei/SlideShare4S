package com.taroid.slideshare4s

import scala.xml.Elem
import scala.xml.factory.XMLLoader
import com.taroid.slideshare4s.SlideShareImpl.Urls

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

  override def searchSlideshows(query: Query, paging: Paging, detailed: Boolean): Seq[Slideshow] = {
    if(query == null) {
      throw new NullPointerException("query must not be null.")
    }
    if(paging == null) {
      throw new NullPointerException("paging must not be null.")
    }

    val url = Utils.createUrlWithParams(Urls.SEARCH,
      "api_key" -> apiKey,
      "ts" -> currentTimeSeconds,
      "hash" -> hash,
      "q" -> query.words,
      "lang" -> query.language,
      "sort" -> query.sortOrder,
      "page" -> paging.page,
      "items_per_page" -> paging.itemsPerPage,
      "detailed" -> (if(detailed) "1" else "0")
    )

    xmlToSlideshows.convert(xmlLoader.load(url))
  }

  def getSlideshowsByTag(tag: String, paging: Paging, detailed: Boolean): Seq[Slideshow] = {
    if(tag == null) {
      throw new NullPointerException("tag must not be null.")
    }
    if(paging == null) {
      throw new NullPointerException("paging must not be null.")
    }

    val url = Utils.createUrlWithParams(Urls.GET_BY_TAG,
      "api_key" -> apiKey,
      "ts" -> currentTimeSeconds,
      "hash" -> hash,
      "tag" -> tag,
      "limit" -> paging.limit,
      "offset" -> paging.offset,
      "detailed" -> (if(detailed) "1" else "0")
    )

    println(url)
    xmlToSlideshows.convert(xmlLoader.load(url))
  }
}

private object SlideShareImpl {
  private object Urls {
    private val BASE = "https://www.slideshare.net/api/2/"
    val SEARCH = BASE + "search_slideshows"
    val GET_BY_TAG = BASE + "get_slideshows_by_tag"
  }
}