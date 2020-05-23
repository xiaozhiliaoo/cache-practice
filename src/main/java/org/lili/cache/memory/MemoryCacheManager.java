package org.lili.cache.memory;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Function;

/**
 * @author lili
 * @date 2020/4/21 12:54
 * @description 缓存管理器：管理缓存配置，缓存名字
 */
public class MemoryCacheManager implements CacheManager {

    public MemoryCacheManager() {
    }

    private static final int NOT_CONFIG = 0;

    /**
     * 缓存名字->缓存
     */
    private final ConcurrentMap<String, LoadingCache> cacheMap = new ConcurrentHashMap<>(16);

    /**
     * 缓存配置：读多写少
     */
    private Set<CacheConfig> cacheConfigCenter = new CopyOnWriteArraySet<>();


    public void addCacheToConfigCenter(CacheConfig cacheConfig) {
        //cacheConfigCenter.addIfAbsent(cacheConfig);
        if (cacheConfig != null) {
            //需要判断缓存名字唯一
            if (cacheConfigCenter.contains(cacheConfig)) {
                throw new RuntimeException("cacheName[" + cacheConfig.getCacheName() + "] must be unique");
            }
            cacheConfigCenter.add(cacheConfig);
        }
    }

    private void addCacheToConfigCenterIfAbsent(CacheConfig cacheConfig) {
        if (cacheConfig != null) {
            //需要判断缓存名字唯一
            if (!cacheConfigCenter.contains(cacheConfig)) {
                cacheConfigCenter.add(cacheConfig);
            }
        }
    }

    @Override
    public <K, V> LoadingCache<K, V> getCache(String cacheName, Function<K, V> buildCacheFunction) {
        Optional<CacheConfig> first = cacheConfigCenter.stream().filter(cacheConfig -> cacheConfig.getCacheName().equals(cacheName)).findFirst();
        if (first.isPresent()) {
            CacheConfig cacheConfig = first.get();
            return getCache(cacheConfig, buildCacheFunction);
        } else {
            throw new RuntimeException(String.format("please config your cache:%s", cacheName));
        }
    }

    @Override
    public <K, V> LoadingCache getCache(CacheConfig cacheConfig, Function<K, V> buildCacheFunction) {
        String cacheName = cacheConfig.getCacheName();
        return cacheMap.computeIfAbsent(cacheName, k -> {
            Caffeine<Object, Object> cacheAssembly = Caffeine.newBuilder();
            if (cacheConfig.getCacheSize() != NOT_CONFIG) {
                cacheAssembly.maximumSize(cacheConfig.getCacheSize());
            }
            if (cacheConfig.getExpireAfterWrite() != NOT_CONFIG) {
                cacheAssembly.expireAfterWrite(cacheConfig.getExpireAfterWrite(), cacheConfig.getExpireAfterWriteTimeUnit());
            }
            if (cacheConfig.getRefreshAfterWrite() != NOT_CONFIG) {
                cacheAssembly.refreshAfterWrite(cacheConfig.getRefreshAfterWrite(), cacheConfig.getRefreshAfterWriteTimeUnit());
            }
            LoadingCache<K, V> cache = cacheAssembly.build(buildCacheFunction::apply);
            addCacheToConfigCenterIfAbsent(cacheConfig);
            return cache;
        });
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(this.cacheMap.keySet());
    }

    @Override
    public void removeCache(String cacheName) {
        cacheMap.remove(cacheName);
    }
}
