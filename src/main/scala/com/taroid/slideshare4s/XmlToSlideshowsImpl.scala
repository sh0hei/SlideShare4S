package com.taroid.slideshare4s

import scala.xml.Elem
import java.text.DateFormat

class XmlToSlideshowsImpl(private val dateFormat: DateFormat) extends XmlToSlideshows {
  def toSlideshows(root: Elem): Seq[Slideshow] = {
    import XmlToSlideshowsImpl._

    (root \ TAG_SLIDESHOW) map { e =>
      Slideshow(
        id = (e \ TAG_ID).text.toLong,
        title = (e \ TAG_TITLE).text,
        username = (e \ TAG_USERNAME).text,
        description = (e \ TAG_DESC).text,
        url = (e \ TAG_URL).text,
        thumbnailUrl = (e \ TAG_THUMB_URL).text,
        created = dateFormat.parse((e \ TAG_CREATED).text),
        updated = dateFormat.parse((e\ TAG_UPDATED).text),
        language = (e \ TAG_LANG).text
      )
    }
  }
}

object XmlToSlideshowsImpl {
  private val TAG_SLIDESHOW = "Slideshow"
  private val TAG_ID = "ID"
  private val TAG_TITLE = "Title"
  private val TAG_USERNAME = "Username"
  private val TAG_DESC = "Description"
  private val TAG_URL = "URL"
  private val TAG_THUMB_URL = "ThumbnailURL"
  private val TAG_CREATED = "Created"
  private val TAG_UPDATED = "Updated"
  private val TAG_LANG = "Language"
}