package com.taroid.slideshare4s

import java.security.MessageDigest

private object Utils {

  def toSha1Hex(str: String): String = {
    if(str == null) {
      throw new NullPointerException("str must not be null.")
    }

    val messageDigest = MessageDigest.getInstance("SHA-1")
    messageDigest.update(str.getBytes)
    return messageDigest.digest.map("%02x".format(_)).mkString
  }

  def createUrlWithParams(url: String, params: (String, Any)*): String = {
    if(url == null) {
      throw new NullPointerException("url must not be null.")
    }

    assert(params != null)
    if(params.exists(p => p == null || p._2 == null)) {
      throw new NullPointerException("params must not contain null.")
    }

    val builder = params.foldLeft(new StringBuilder(url).append("?")) {
      (b, p) => b.append(p._1).append("=").append(p._2.toString).append("&")
    }

    return builder.substring(0, builder.size - 1)
  }
}