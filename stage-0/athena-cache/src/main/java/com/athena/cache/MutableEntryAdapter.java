/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import javax.cache.Cache;
import javax.cache.processor.MutableEntry;

/**
 * @author shenlong
 */
public class MutableEntryAdapter<K, V> implements MutableEntry<K, V> {

    private final K key;

    private final Cache<K, V> cache;

    public MutableEntryAdapter(K key, Cache<K, V> cache) {
        this.key = key;
        this.cache = cache;
    }

    @Override
    public boolean exists() {
        return cache.containsKey(key);
    }

    @Override
    public void remove() {
        cache.remove(key);
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return cache.get(key);
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }

    @Override
    public void setValue(V value) {
        cache.put(key, value);
    }

    public static <K, V> MutableEntryAdapter<K, V> of(K key, Cache<K, V> cache) {
        return new MutableEntryAdapter<>(key, cache);
    }
}