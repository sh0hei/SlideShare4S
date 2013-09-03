package com.taroid.slideshare4s

import org.specs2.mutable._
import org.specs2.mock._
import scala.xml.factory.XMLLoader
import scala.xml.Elem
import org.specs2.specification.BeforeExample

class SlideShareImplTest extends Specification with BeforeExample with Mockito {

  sequential

  private var xmlLoader: XMLLoader[Elem] = null

  private var xmlToSlideshows: XmlToSlideshows = null

  private var ss: SlideShareImpl = null

  def before = {
    xmlLoader = mock[XMLLoader[Elem]]
    xmlToSlideshows = mock[XmlToSlideshows]
    ss = new SlideShareImpl("apiKey", "sharedSecret", xmlLoader, xmlToSlideshows)
  }

  "searchSlideshows" should {
    "引数にnullを渡すと例外を投げる" in {
      {
        ss.searchSlideshows(null)
      } must throwA[IllegalArgumentException]
    }

    "引数の検索クエリの各プロパティを参照する" in {
      // setup
      val query = mock[Query]
      query.words returns ""
      query.page returns 0
      query.itemsPerPage returns 0
      query.language returns ""
      query.sortOrder returns SortOrder.RELEVANCE

      // exercise
      ss.searchSlideshows(query)

      // verify
      there was one(query).words
      there was one(query).page
      there was one(query).itemsPerPage
      there was one(query).language
      there was one(query).sortOrder
    }

    "XmlLoader#loadでXMLを取得した後にXmlToSlideshows#toSlideshowsを呼び出す" in {
      ss.searchSlideshows(Query("hoge"))
      there was one(xmlLoader).load(anyString) andThen one(xmlToSlideshows).toSlideshows(any[Elem])
    }
  }
}
