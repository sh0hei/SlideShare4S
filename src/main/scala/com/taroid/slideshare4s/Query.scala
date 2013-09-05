package com.taroid.slideshare4s

import com.taroid.slideshare4s.SortOrder.SortOrder

/**
 * スライドの検索クエリ
 * @param words 検索ワード
 * @param language 言語
 * @param sortOrder ソート順
 */
case class Query(
  words: String,
  language: String = "**",
  sortOrder: SortOrder = SortOrder.RELEVANCE
) {
  require(words != null)
  require(words.size > 0)
  require(language != null)
  require(sortOrder != null)
}