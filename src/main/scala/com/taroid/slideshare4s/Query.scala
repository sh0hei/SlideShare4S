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
  if(words == null) {
    throw new NullPointerException("words must not be null.")
  }

  require(words.size > 0)

  if(language == null) {
    throw new NullPointerException("language must not be null.")
  }

  if(sortOrder == null) {
    throw new NullPointerException("sortOrder must not be null.")
  }
}