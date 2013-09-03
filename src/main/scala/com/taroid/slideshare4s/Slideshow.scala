package com.taroid.slideshare4s

import java.util.Date

/**
 * スライド
 * @param id ID
 * @param title タイトル
 * @param username ユーザ名
 * @param description 説明
 * @param url 掲載URL
 * @param thumbnailUrl サムネイルURL
 * @param created 作成日
 * @param updated 更新日
 * @param language 言語
 * @param embed 埋め込みタグ
 */
case class Slideshow(
  id: Long,
  title: String,
  username: String,
  description: String,
  url: String,
  thumbnailUrl: String,
  created: Date,
  updated: Date,
  language: String,
  embed: String
) {
  require(title != null)
  require(username != null)
  require(description != null)
  require(url != null)
  require(thumbnailUrl != null)
  require(created != null)
  require(updated != null)
  require(language != null)
  require(embed != null)
}
