package com.ieening.learnGenricConatiner;

import java.util.LinkedHashMap;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int maxSize;

    public LRUCache(int maxSize) {
        super(16, 0.75f, true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }

    public static void main(String[] args) {
        LRUCache<String, Object> cache = new LRUCache<>(3);
        cache.put("a", "abstract");
        cache.put("b", "basic");
        cache.put("c", "call");
        cache.get("a");
        cache.put("d", "call");
        System.out.println(cache);
    }
}
