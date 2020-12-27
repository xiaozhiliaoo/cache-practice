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
public class Cache {

    private static ThreadLocal<String> companyId = new InheritableThreadLocal<>();

    private static LoadingCache<String, String> graphs = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .refreshAfterWrite(10, TimeUnit.SECONDS)
            //.buildAsync((key -> createExpensiveGraph((String) key)));
            .build(key -> createExpensiveGraph(key));


    private static String createExpensiveGraph(String key) {
        companyId.set(key);
        String name = Thread.currentThread().getName();
        System.out.println("notify createExpensiveGraph ..., threadName:" + name + ", companyId:" + companyId.get());
        String s = key + ":value" + companyId.get();
        companyId.remove();
        return s;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(500);
            if (i % 10 == 0) {
                System.out.println("refresh.... " + i);
                //异步，但是丢失了companyId
                graphs.refresh("lili" + i);
                //graphs.invalidate("lili");
            }
            System.out.println("k-v:" + graphs.get("lili" + i));
            System.out.println("companyID:" + companyId.get());
        }
    }
}
