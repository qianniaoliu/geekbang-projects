/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import javax.cache.Cache;

/**
 * @author shenlong
 */
public class ExpireEntry<K, V> implements Cache.Entry<K, V> {

    private K key;

    private V value;

    private long timestamp;

    public ExpireEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > timestamp;
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }

    public static <K, V> ExpireEntry<K, V> of(K key, V value) {
        return new ExpireEntry<>(key, value);
    }
}