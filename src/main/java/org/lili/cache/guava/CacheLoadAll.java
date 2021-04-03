package org.lili.cache.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lili
 * @date 2021/1/1 18:03
 * @see
 * @since
 */
public class CacheLoadAll {

    private static LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .refreshAfterWrite(2, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return createExpensiveGraph(key);
                }

                @Override
                public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {
                    return super.loadAll(keys);
                }
            });

    private static String createExpensiveGraph(String key) {
        return key + "_" + System.currentTimeMillis();
    }

    public static void main(String[] args) throws ExecutionException {

        for (int i = 1; i <= 1000; i++) {
            System.out.println(graphs.get("lili" + i));
        }

        graphs.get("lili", () -> "null");

        List<String> lili = new ArrayList<>();
        lili.add("lili1");
        lili.add("lili2");
        lili.add("lili3");
        lili.add("lili4");
        System.out.println(graphs.getAll(lili).asMultimap());


    }
}
