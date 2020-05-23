package org.lili.cache.memory;

import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.Collection;
import java.util.function.Function;

/**
 * @author lili
 * @date 2020/4/21 13:10
 * @description 这里只对caffeine进行简单封装满足简单使用，优秀的封装参考{@link org.springframework.cache.CacheManager},
 * 这里不直接用spring-cache，是因为基础缓存需要脱离spring框架，你可以用spring管理你的基础组件，但是不能关联他
 */
public interface CacheManager {
    /**
     * @param cacheName          缓存名字
     * @param buildCacheFunction 构建缓存的函数，k是缓存k，v是缓存值
     * @param <K>                缓存k类型
     * @param <V>                缓存v类型
     * @return 缓存
     */
    <K, V> LoadingCache getCache(String cacheName, Function<K, V> buildCacheFunction);


    /**
     * 删除某个缓存
     *
     * @param cacheName 缓存名字
     * @return
     */
    void removeCache(String cacheName);


    /**
     * @param cacheConfig        缓存配置
     * @param buildCacheFunction 构建缓存的函数，k是缓存k，v是缓存值
     * @param <K>                缓存k类型
     * @param <V>                缓存v类型
     * @return 缓存
     */
    <K, V> LoadingCache getCache(CacheConfig cacheConfig, Function<K, V> buildCacheFunction);


    /**
     * 获取所有缓存名字
     *
     * @return 缓存名字
     */
    Collection<String> getCacheNames();


}
