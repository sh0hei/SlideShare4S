package com.taroid.slideshare4s

import java.util.Date
import com.taroid.slideshare4s.Slideshow.Extra

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
 * @param extra 付加情報
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
  embed: String,
  extra: Option[Extra] = None
) {
  if(title == null) {
    throw new NullPointerException("title must not be null.")
  }
  if(username == null) {
    throw new NullPointerException("username must not be null.")
  }
  if(description == null) {
    throw new NullPointerException("description must not be null.")
  }
  if(url == null) {
    throw new NullPointerException("url must not be null.")
  }
  if(thumbnailUrl == null) {
    throw new NullPointerException("thumbnailUrl must not be null.")
  }
  if(created == null) {
    throw new NullPointerException("created must not be null.")
  }
  if(updated == null) {
    throw new NullPointerException("updated must not be null.")
  }
  if(language == null) {
    throw new NullPointerException("language must not be null.")
  }
  if(embed == null) {
    throw new NullPointerException("embed must not be null.")
  }
  if(extra == null) {
    throw new NullPointerException("extra must not be null.")
  }
}

object Slideshow {

  /**
   * スライドに関する付加情報です。
   *
   * @param tags タグ
   */
  case class Extra(
    tags: Seq[String]
  ) {
    if(tags == null) {
      throw new NullPointerException("tags must not be null.")
    }
  }
}
