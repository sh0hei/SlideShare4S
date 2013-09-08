package com.taroid.slideshare4s

import scala.xml.Elem
import java.text.DateFormat

private class XmlToSlideshowsImpl(private val dateFormat: DateFormat) extends XmlToSlideshows {
  if(dateFormat == null) {
    throw new NullPointerException("dateFormat must not be null.")
  }

  def convert(root: Elem): Seq[Slideshow] = {
    if(root == null) {
      throw new NullPointerException("root must not be null.")
    }

    import XmlToSlideshowsImpl._

    (root \ TAG_SLIDESHOW) map { e =>
      val tags = (e \ TAG_TAGS  \ TAG_TAG).map(_.text)
      val extra = if(tags.size == 0) None else Some(Slideshow.Extra(tags))

      Slideshow(
        id = (e \ TAG_ID).text.toLong,
        title = (e \ TAG_TITLE).text,
        username = (e \ TAG_USERNAME).text,
        description = (e \ TAG_DESC).text,
        url = (e \ TAG_URL).text,
        thumbnailUrl = (e \ TAG_THUMB_URL).text,
        created = dateFormat.parse((e \ TAG_CREATED).text),
        updated = dateFormat.parse((e\ TAG_UPDATED).text),
        language = (e \ TAG_LANG).text,
        embed = (e \ TAG_EMBED).text,
        extra = extra
      )
    }
  }
}

private object XmlToSlideshowsImpl {
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
  private val TAG_EMBED = "Embed"
  private val TAG_TAGS = "Tags"
  private val TAG_TAG = "Tag"
}