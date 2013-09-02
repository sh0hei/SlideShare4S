package com.taroid.slideshare4s

import org.specs2.mutable._

class UtilsTest extends Specification {

  "toSha1Hex" should {
    "nullを渡すと例外を投げる" in {
      {Utils.toSha1Hex(null)} must throwA[IllegalArgumentException]
    }

    "文字列「Hello」を渡すとSHA-1で変換された文字列を返す" in {
      Utils.toSha1Hex("Hello") mustEqual "f7ff9e8b7bb2e09b70935a5d785e0cc5d9d0abf0"
    }
  }

  "createUrlWithParams" should {
    "第1引数にnullを渡すと例外を投げる" in {
      {Utils.createUrlWithParams(null)} must throwA[IllegalArgumentException]
    }

    "第2引数以降にnullを渡すと例外を投げる" in {
      {Utils.createUrlWithParams("", null)} must throwA[IllegalArgumentException]
      {Utils.createUrlWithParams("", "" -> "", null)} must throwA[IllegalArgumentException]
    }

    "第2引数以降の要素にnullを指定すると例外を投げる" in {
      {Utils.createUrlWithParams("", "" -> null)} must throwA[IllegalArgumentException]
      {Utils.createUrlWithParams("", "" -> "", "" -> null)} must throwA[IllegalArgumentException]
    }

    "第2引数以降がない場合は第1引数をそのまま返す" in {
      Utils.createUrlWithParams("http://example.com/") mustEqual "http://example.com/"
      Utils.createUrlWithParams("") mustEqual ""
      Utils.createUrlWithParams("foo") mustEqual "foo"
    }

    "第2引数の値をkey=valueのように展開して第1引数の末尾に?を挟んで連結したものを返す" in {
      Utils.createUrlWithParams("http://example.com/", "q" -> "hoge") mustEqual "http://example.com/?q=hoge"
    }

    "第3引数以降の値をkey=valueのように展開して&で挟んで連結したものを返す" in {
      Utils.createUrlWithParams("http://example.com/", "q" -> "hoge", "page" -> 1) mustEqual "http://example.com/?q=hoge&page=1"
      Utils.createUrlWithParams("AAA", "a" -> "foo", "b" -> "bar", "c" -> "baz") mustEqual "AAA?a=foo&b=bar&c=baz"
    }
  }
}
