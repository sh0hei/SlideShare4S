package com.taroid.slideshare4s

import org.specs2.mutable._
import org.specs2.mock._
import scala.xml.factory.XMLLoader
import scala.xml.Elem
import org.specs2.specification.BeforeExample

class SlideShareImplTest extends Specification with BeforeExample with Mockito {

  sequential

  private var xmlLoader: XMLLoader[Elem] = null

  private var xmlToSlideshows: XmlToSlideshowsImpl = null

  private var ss: SlideShareImpl = null

  def before = {
    xmlLoader = mock[XMLLoader[Elem]]
    xmlToSlideshows = mock[XmlToSlideshowsImpl]
    ss = new SlideShareImpl("apiKey", "sharedSecret", xmlLoader, xmlToSlideshows)
  }

  "searchSlideshows" should {
    "引数にnullを渡すと例外を投げる" in {
      {ss.searchSlideshows(null, mock[Paging])} must throwA[NullPointerException];
      {ss.searchSlideshows(mock[Query], null)} must throwA[NullPointerException]
    }

    "引数の検索クエリの各プロパティを参照する" in {
      // setup
      val query = mock[Query]
      query.words returns ""
      query.language returns ""
      query.sortOrder returns SortOrder.RELEVANCE

      // exercise
      ss.searchSlideshows(query, Paging(12, 1))

      // verify
      there was one(query).words
      there was one(query).language
      there was one(query).sortOrder
    }

    "引数のページングの各プロパティを参照する" in {
      // setup
      val paging = mock[Paging]
      paging.page returns 1
      paging.itemsPerPage returns 12

      // exercise
      ss.searchSlideshows(Query("hoge"), paging)

      // verify
      there was one(paging).page
      there was one(paging).itemsPerPage
    }

    "XmlLoader#loadでXMLを取得した後にXmlToSlideshows#toSlideshowsを呼び出す" in {
      ss.searchSlideshows(Query("hoge"), Paging(12, 1))
      there was one(xmlLoader).load(anyString) andThen one(xmlToSlideshows).convert(any[Elem])
    }
  }

  "getSlideshowsByTag" should {
    "引数にnullを渡すと例外を投げる" in {
      {ss.getSlideshowsByTag(null, mock[Paging])} must throwA[NullPointerException];
      {ss.getSlideshowsByTag("tag", null)} must throwA[NullPointerException]
    }

    "引数のページングのlimitとoffsetを参照する" in {
      // setup
      val paging = mock[Paging]
      paging.page returns 1
      paging.itemsPerPage returns 12

      // exercise
      ss.getSlideshowsByTag("tag", paging)

      // verify
      there was one(paging).limit
      there was one(paging).offset
    }

    "XmlLoader#loadでXMLを取得した後にXmlToSlideshows#toSlideshowsを呼び出す" in {
      ss.getSlideshowsByTag("tags", Paging(12, 1))
      there was one(xmlLoader).load(anyString) andThen one(xmlToSlideshows).convert(any[Elem])
    }
  }
}
