https://www.thestrangeloop.com/2011/concurrent-caching-at-google.html
两个人是同事。
Caffeine: Benjamin Manes
Guava: Charles Fry

MapMaker is Google's premier data structure for in-memory caching on the JVM. 
This presentation will cover lock amortization as a simpler alternative to 
techniques such as lock-free and elimination-based data structures. We'll describe
how amortized analysis can be used to avoid lock contention and how it is leveraged
to support expiration, soft/weak reference collection, and bounded caches.

https://github.com/google/guava/wiki/CachesExplained

Cahce Select:

https://mvnrepository.com/open-source/cache-implementations

Api Select:

https://java-source.net/open-source/cache-solutions

https://javadoc.io/doc/com.github.ben-manes.caffeine/caffeine/latest/index.html

https://javadoc.io/doc/javax.cache/cache-api/latest/index.html

https://guava.dev/releases/21.0/api/docs/index.html?com/google/common/cache/Cache.html

https://en.wikipedia.org/wiki/Cache_replacement_policies

Belady现象:缓存大小变大，缓存的淘汰率反而增加。
页面帧数 = 缓存大小
页面置换算法 = 缓存淘汰算法
缺页错误 = 缓存不命中
缺页错误率 = 缓存命中率