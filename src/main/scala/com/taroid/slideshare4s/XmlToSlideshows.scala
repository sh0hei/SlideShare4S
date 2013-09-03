package com.taroid.slideshare4s

import scala.xml.Elem

trait XmlToSlideshows {

  def toSlideshows(root: Elem): Seq[Slideshow]
}
