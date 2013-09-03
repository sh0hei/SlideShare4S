package com.taroid.slideshare4s

import scala.xml.Elem

trait XmlToSlideshows {

  def convert(root: Elem): Seq[Slideshow]
}
