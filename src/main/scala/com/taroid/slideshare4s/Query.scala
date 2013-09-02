package com.taroid.slideshare4s

import com.taroid.slideshare4s.SortOrder.SortOrder

/**
 * スライドの検索クエリ
 * @param words 検索ワード
 * @param page ページ
 * @param itemsPerPage ページあたりのスライド数
 * @param language 言語
 * @param sortOrder ソート順
 */
case class Query(
  words: String,
  page: Int = 1,
  itemsPerPage: Int = 12,
  language: String = "**",
  sortOrder: SortOrder = SortOrder.RELEVANCE
) {
  require(words != null)
  require(words.size > 0)
  require(page > 0)
  require(itemsPerPage > 0)
  require(language != null)
  require(sortOrder != null)
}