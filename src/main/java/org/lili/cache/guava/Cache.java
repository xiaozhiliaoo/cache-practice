package org.lili.cache.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lili
 * @date 2020/4/21 22:57
 * @description
 * @notes
 */
public class Cache {
    private static LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .refreshAfterWrite(2,TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                    return createExpensiveGraph(key);
                }
            });

    private static String createExpensiveGraph(String key) {
        System.out.println("load key...."+System.currentTimeMillis());
        return "123";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        createExpensiveGraph("lili");
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1000);
            System.out.println(graphs.get("lili"));
        }
        Thread.sleep(100000);
    }
}
