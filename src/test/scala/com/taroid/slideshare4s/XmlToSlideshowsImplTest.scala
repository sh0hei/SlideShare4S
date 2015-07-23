package com.taroid.slideshare4s

import java.text.{DateFormat, SimpleDateFormat}
import java.util.{Locale, Date}

import org.specs2.mock._
import org.specs2.mutable._

import scala.xml.XML

class XmlToSlideshowsImplTest extends Specification with Mockito {

  private val xml = XML.loadString("""
                                     |<Slideshows>
                                     | <Slideshow>
                                     |  <ID>111111</ID>
                                     |  <Title>aaa</Title>
                                     |  <Username>bbb</Username>
                                     |  <Description>ccc</Description>
                                     |  <URL>ddd</URL>
                                     |  <ThumbnailURL>eee</ThumbnailURL>
                                     |  <Created>Thu Jan 01 00:00:00 +0900 2013</Created>
                                     |  <Updated>Thu Jan 01 01:00:00 +0900 2013</Updated>
                                     |  <Language>ff</Language>
                                     |  <Embed>ggg</Embed>
                                     | </Slideshow>
                                     |</Slideshows>
                                   """.stripMargin)

  "convert" should {
    "引数にnullを渡すと例外を投げる" in {
      {new XmlToSlideshowsImpl(mock[DateFormat]).convert(null)} must throwA[NullPointerException]
    }

    "引数にスライド情報を渡すとスライド1つにつきDateFormat#parseを2回呼び出す" in {
      val dateFormat = mock[DateFormat]
      dateFormat.parse(anyString) returns new Date(0)

      new XmlToSlideshowsImpl(dateFormat).convert(xml)

      there was two(dateFormat).parse(anyString)
    }

    "引数にスライド情報を渡すとSlideshowのリストを返す" in {
      val dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US)
      val slideshows = new XmlToSlideshowsImpl(dateFormat).convert(xml)

      slideshows must haveTheSameElementsAs(List(Slideshow(
        id = 111111,
        title = "aaa",
        username = "bbb",
        description = "ccc",
        url = "ddd",
        thumbnailUrl = "eee",
        created = dateFormat.parse("Thu Jan 01 00:00:00 +0900 2013"),
        updated = dateFormat.parse("Thu Jan 01 01:00:00 +0900 2013"),
        language = "ff",
        embed = "ggg",
        extra = None
      )))
    }
  }
}
