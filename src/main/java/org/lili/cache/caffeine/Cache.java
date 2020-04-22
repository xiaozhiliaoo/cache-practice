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
    private static LoadingCache<String, String> graphs = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .refreshAfterWrite(5, TimeUnit.SECONDS)
            .build(key -> createExpensiveGraph(key));

    private static String createExpensiveGraph(String key) {
        return "123";
    }

    public static void main(String[] args) throws InterruptedException {
        createExpensiveGraph("lili");
        System.out.println(graphs.get("lili"));
        Thread.sleep(100000);
    }
}
