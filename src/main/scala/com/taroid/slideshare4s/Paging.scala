package com.taroid.slideshare4s

/**
 * ページ制御用クラス
 *
 * @param itemsPerPage ページあたりのスライド数（最大100）
 * @param page ページ番号
 */
case class Paging(itemsPerPage: Int = 12, page: Int = 1) {
  require(itemsPerPage > 0 && itemsPerPage <= 100, "itemsPerPage must be in 1 to 100.")
  require(page > 0, "page must be greater than zero.")

  def getLimit: Int = itemsPerPage
  def getOffset: Int = itemsPerPage * (page - 1)
}