package org.lili.cache.mycache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lili
 * @date 2020/7/12 15:07
 * @notes
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int size;

    public LRUCache(int size) {
        super(size);
        this.size = size;
    }

    public LRUCache(int size, float loadFactor, boolean accessOrder) {
        super(size, loadFactor, accessOrder);
        this.size = size;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > size;
    }

    public static void main(String[] args) {
        //accessOrder 必须设置为true 才可以设置缓存。
        LRUCache lruCache = new LRUCache(3, 0.75f, true);
        lruCache.put("1", "1");
        lruCache.put("2", "2");
        lruCache.put("3", "3");
        System.out.println(lruCache);
        lruCache.get("1");
        lruCache.get("2");
        System.out.println(lruCache);
        lruCache.put("4", "4");
        lruCache.put("5", "5");
        System.out.println(lruCache);

//        LinkedHashMap<String,String> s = new LinkedHashMap<>(2);
//        s.put("1","1");
//        s.put("2","1");
//        s.put("3","3");
//        s.put("15","3");
//        System.out.println(s);
    }
}
