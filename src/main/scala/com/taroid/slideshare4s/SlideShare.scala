package com.taroid.slideshare4s

import java.text.SimpleDateFormat

/**
 * SlideShareのAPI
 */
trait SlideShare {
  def apiKey: String
  def sharedSecret: String

  protected def currentTimeSeconds: Long = System.currentTimeMillis / 1000
  protected def hash: String = Utils.toSha1Hex(sharedSecret + currentTimeSeconds)

  /**
   * 指定されたクエリに基づいてスライドを検索します。<br>
   * ヒットしたスライドのシーケンスを返します。
   * @param query 検索クエリ
   * @param paging 検索ページ
   * @return 検索にヒットしたスライドのシーケンス
   */
  def searchSlideshows(query: Query, paging: Paging): Seq[Slideshow]
}

object SlideShare {

  /**
   * {@link SlideShare}の具象クラスのインスタンスを返すファクトリメソッドです。
   * @param apiKey SlideShare API用のAPI KEY
   * @param sharedSecret SlideShare API用のSHARED SECRET
   * @return SlideShareインスタンス
   */
  def apply(apiKey: String, sharedSecret: String): SlideShare = {
    require(apiKey != null)
    require(apiKey.size > 0)
    require(sharedSecret != null)
    require(sharedSecret.size > 0)

    val dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy")
    val xmlToSlideshows = new XmlToSlideshowsImpl(dateFormat)

    return new SlideShareImpl(apiKey, sharedSecret, scala.xml.XML, xmlToSlideshows)
  }
}

