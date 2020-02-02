package org.lili.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @author lili
 * @date 2020/2/2 19:50
 * @description https://github.com/ben-manes/caffeine/wiki/Design
 * https://github.com/ben-manes/caffeine/wiki/Benchmarks
 */
public class Cache  {
    LoadingCache<String, String> graphs = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .refreshAfterWrite(1, TimeUnit.MINUTES)
            .build(key -> createExpensiveGraph(key));

    private String createExpensiveGraph(String key) {
        return null;
    }
}
