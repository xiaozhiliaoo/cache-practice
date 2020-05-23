package org.lili.cache.memory;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.TimeUnit;

/**
 * @author lili
 * @date 2020/4/22 10:56
 * @description 单个缓存配置
 */
@Data
@EqualsAndHashCode
public class CacheConfig {

    /**
     * 缓存大小
     */
    private long cacheSize;

    private String cacheName = "defaultCache";

    /**
     * 缓存描述
     */
    private String description;

    /**
     * 缓存过期时间
     */
    private int expireAfterWrite ;

    /**
     * 缓存刷新时间
     */
    private int refreshAfterWrite;

    private TimeUnit expireAfterWriteTimeUnit;

    private TimeUnit refreshAfterWriteTimeUnit;


    public long getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(long cacheSize) {
        this.cacheSize = cacheSize;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public int getExpireAfterWrite() {
        return expireAfterWrite;
    }

    public void setExpireAfterWrite(int expireAfterWrite, TimeUnit timeUnit) {
        this.expireAfterWrite = expireAfterWrite;
        this.expireAfterWriteTimeUnit = timeUnit;
    }


    public int getRefreshAfterWrite() {
        return refreshAfterWrite;
    }

    public void setRefreshAfterWrite(int refreshAfterWrite, TimeUnit timeUnit) {
        this.refreshAfterWrite = refreshAfterWrite;
        this.refreshAfterWriteTimeUnit = timeUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimeUnit getExpireAfterWriteTimeUnit() {
        return expireAfterWriteTimeUnit;
    }

    public TimeUnit getRefreshAfterWriteTimeUnit() {
        return refreshAfterWriteTimeUnit;
    }


}
