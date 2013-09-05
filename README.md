# SlideShare4S

**SlideShare4S** is a library for Scala to use SlideShare API.

## Features

* Get some information of slideshows
 * ID
 * Title
 * User name
 * Description
 * URL
 * Thumbnail URL
 * Creation time
 * Updated time
 * Language
 * Embed tags
* Search by keywords and languages

## Sample

```Scala
import com.taroid.slideshare4s.{SortOrder, SlideShare, Query}

object Main {
  def main(args: Array[String]) {
    val apiKey = "YOUR_API_KEY"
    val sharedSecret = "YOUR_SHARED_SECRET"

    val ss = SlideShare(apiKey, sharedSecret)
    val query = Query(words="scala", language="ja", sortOrder=SortOrder.LATEST)
    val paging = Paging(itemsPerPage=12, page=1)

    ss.searchSlideshows(query, paging).foreach(println)
  }
}
```

## License

SlideShare4S is licensed under the Apache Licenses version 2.0.
Please feel free to use it in your projects.
