/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import com.athena.cache.event.CacheEntryEventPublisher;

import javax.cache.CacheManager;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheWriter;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlong
 */
public class InMemoryCache<K, V> extends AbstractCache<K, V> {

    private volatile boolean enable = true;

    private final Map<K, V> cacheData = new ConcurrentHashMap<>();

    public InMemoryCache(CacheLoader<K, V> cacheLoader, CacheWriter<K, V> cacheWriter,
                         CompleteConfiguration<K, V> configuration, CacheManager cacheManager,
                         String cacheName) {
        super(cacheLoader, cacheWriter, configuration, cacheManager, cacheName);
    }

    @Override
    protected ExpireEntry<K, V> getEntry(K key) {
        V value = cacheData.get(key);
        return Optional.ofNullable(value)
                .map(item -> ExpireEntry.of(key, item)).orElse(null);
    }

    @Override
    protected boolean containsEntry(K key) {
        return cacheData.containsKey(key);
    }

    @Override
    protected void putEntry(K key, V value) {
        cacheData.put(key, value);
    }

    @Override
    protected boolean deleteEntry(K key) {
        cacheData.remove(key);
        return true;
    }

    @Override
    protected Collection<K> getAllKeys() {
        return cacheData.keySet();
    }

    @Override
    protected void removeAllEntry() {
        cacheData.clear();
    }

    /**
     * Close.
     */
    @Override
    public void close() {
        this.enable = false;
    }

    /**
     * Is closed boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isClosed() {
        return !this.enable;
    }

    /**
     * Unwrap t.
     *
     * @param <T>   the type parameter
     * @param clazz the clazz
     * @return the t
     */
    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }
}