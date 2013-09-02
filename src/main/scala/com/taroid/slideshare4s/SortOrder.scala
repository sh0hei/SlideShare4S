package com.taroid.slideshare4s

/**
 * ソート順列挙型
 */
object SortOrder extends Enumeration {
  type SortOrder = Value

  /** 関連順 */
  val RELEVANCE = Value("relevance")

  /** 表示回数順 */
  val MOSTVIEWED = Value("mostviewd")

  /** ダウンロード数順 */
  val MOSTDOWNLOADED = Value("mostdownloaded")

  /** 新しい順 */
  val LATEST = Value("latest")
}
