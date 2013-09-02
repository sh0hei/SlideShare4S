# SlideShare4S

**SlideShare4S** is a library for Scala to use SlideShare API.

## Sample

```Scala
import com.taroid.slideshare4s.{SortOrder, SlideShare, Query}

object Main {
  def main(args: Array[String]) {
    val apiKey = System.getenv("SLIDESHARE_API_KEY")
    val sharedSecret = System.getenv("SLIDESHARE_SHARED_SECRET")

    val ss = SlideShare(apiKey, sharedSecret)
    val query = Query(words = "scala", itemsPerPage = 10, language = "ja", sortOrder = SortOrder.LATEST)

    ss.searchSlideshow(query).foreach(println)
  }
}
```