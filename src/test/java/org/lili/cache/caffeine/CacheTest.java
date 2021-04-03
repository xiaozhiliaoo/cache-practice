package org.lili.cache.caffeine;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *
 * 不错：https://stackoverflow.com/questions/41956363/guava-cache-expireafteraccess-issue  单元测试demo
 *
 * @author lili
 * @date 2021/1/6 2:22
 * @see
 * @since
 */
public class CacheTest {

    String createExpensiveGraph(String key) {
        System.out.println("load key...." + System.currentTimeMillis());
        return key + "123";
    }

    @Test
    public void testExpireAfterWrite() throws ExecutionException, InterruptedException {
        //写入1个key，
        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println("remove");
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return createExpensiveGraph(key);
                    }
                });
        //创建时间
        graphs.put("lili","ssss");
        ConcurrentMap<String, String> view = graphs.asMap();
        System.out.println(view);
        TimeUnit.SECONDS.sleep(5);
        System.out.println(view);
        System.out.println(graphs.get("lili"));
    }


    @Test
    public void testExpireAfterAccess() throws ExecutionException, InterruptedException {
        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return createExpensiveGraph(key);
                    }
                });

        graphs.put("lili","ssss");
        TimeUnit.SECONDS.sleep(3);
        System.out.println(graphs.asMap());
    }


    @Test
    public void testRefreshAfterWrite() throws ExecutionException, InterruptedException {
        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println("remove");
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return createExpensiveGraph(key);
                    }
                });

        graphs.put("lili","ssss");
        TimeUnit.SECONDS.sleep(5);
        //5s之后，因为没有访问lili，所以缓存不会失效
        System.out.println(graphs.asMap());
        //5s之后，因为有访问lili，所以缓存会失效
        System.out.println(graphs.get("lili"));
    }

}