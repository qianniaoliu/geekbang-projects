/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache.event;

import javax.cache.Cache;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.EventType;
import java.util.Objects;

/**
 * @author shenlong
 */
public class GenericCacheEntryEvent<K, V> extends CacheEntryEvent<K, V> {

    private final K key;

    private final V value;

    private final V oldValue;

    /**
     * Constructs a cache entry event from a given cache as source
     *
     * @param source   the cache that originated the event
     * @param key
     * @param value
     * @param oldValue
     */
    public GenericCacheEntryEvent(Cache source, K key, V value, V oldValue, EventType eventType) {
        super(source, eventType);
        this.key = key;
        this.value = value;
        this.oldValue = oldValue;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }

    @Override
    public V getOldValue() {
        return this.oldValue;
    }

    @Override
    public boolean isOldValueAvailable() {
        return Objects.nonNull(this.oldValue);
    }

    /**
     * created event
     *
     * @param cache
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> GenericCacheEntryEvent<K, V> createdEvent(Cache<K, V> cache, K key, V value) {
        return new GenericCacheEntryEvent<>(cache, key, value, null, EventType.CREATED);
    }

    /**
     * updated event
     *
     * @param cache
     * @param key
     * @param value
     * @param oldValue
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> GenericCacheEntryEvent<K, V> updatedEvent(Cache<K, V> cache, K key, V value, V oldValue) {
        return new GenericCacheEntryEvent<>(cache, key, value, oldValue, EventType.UPDATED);
    }

    /**
     * removed event
     *
     * @param cache
     * @param key
     * @param oldValue
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> GenericCacheEntryEvent<K, V> removedEvent(Cache<K, V> cache, K key, V oldValue) {
        return new GenericCacheEntryEvent<>(cache, key, null, oldValue, EventType.REMOVED);
    }

    /**
     * expired event
     *
     * @param cache
     * @param key
     * @param oldValue
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> GenericCacheEntryEvent<K, V> expiredEvent(Cache<K, V> cache, K key, V oldValue) {
        return new GenericCacheEntryEvent<>(cache, key, null, oldValue, EventType.EXPIRED);
    }

    @Override
    public String toString() {
        return "GenericCacheEntryEvent{" +
                "key=" + key +
                ", value=" + value +
                ", oldValue=" + oldValue +
                ", source=" + source +
                '}';
    }
}