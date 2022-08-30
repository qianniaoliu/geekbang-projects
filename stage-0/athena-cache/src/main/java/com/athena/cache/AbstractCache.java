/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import com.athena.cache.event.CacheEntryEventPublisher;
import com.athena.cache.event.ConditionalCacheEntryListenerAdapter;
import com.athena.cache.event.GenericCacheEntryEvent;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @author shenlong
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {

    private CacheLoader<K, V> cacheLoader;

    private CacheWriter<K, V> cacheWriter;

    private CompleteConfiguration<K, V> configuration;

    private CacheManager cacheManager;

    private String cacheName;

    private CacheEntryEventPublisher publisher;

    public AbstractCache(CacheLoader<K, V> cacheLoader, CacheWriter<K, V> cacheWriter,
                         CompleteConfiguration<K, V> configuration, CacheManager cacheManager, String cacheName) {
        this.cacheLoader = cacheLoader;
        this.cacheWriter = cacheWriter;
        this.configuration = configuration;
        this.cacheManager = cacheManager;
        this.cacheName = cacheName;
        this.publisher = new CacheEntryEventPublisher();
    }

    @Override
    public V get(K key) {
        assetNotClose();
        assetNotNull(key);
        ExpireEntry<K, V> entry = getEntry(key);
        // if cache is expired, remove this key
        if (Objects.nonNull(entry) && entry.isExpired()) {
            remove(key);
            publishExpiredEvent(key, entry.getValue());
            return null;
        }
        // if entry=null and read through is true, load value from cacheLoader
        if (entry == null && configuration.isReadThrough()) {
            return loadValue(key);
        }
        return getValue(entry);
    }

    private void assetNotClose() {
        if (isClosed()) {
            throw new IllegalStateException("this cache is closed");
        }
    }

    private void assetNotNull(Object value) {
        if (Objects.isNull(value)) {
            throw new NullPointerException("the value is null");
        }
    }

    private V loadValue(K key) {
        V value = cacheLoader.load(key);
        if (Objects.nonNull(value)) {
            putEntry(key, value);
        }
        return value;
    }

    protected V getValue(ExpireEntry<K, V> entry) {
        return Objects.isNull(entry) ? null : entry.getValue();
    }

    protected abstract ExpireEntry<K, V> getEntry(K key);

    @Override
    public Map<K, V> getAll(Set<? extends K> keys) {
        assetNotClose();
        Map<K, V> result = new HashMap<>();
        for (K key : keys) {
            assetNotNull(key);
            result.put(key, get(key));
        }
        return result;
    }

    @Override
    public boolean containsKey(K key) {
        assetNotClose();
        assetNotNull(key);
        return containsEntry(key);
    }

    protected abstract boolean containsEntry(K key);

    @Override
    public void loadAll(Set<? extends K> keys, boolean replaceExistingValues, CompletionListener completionListener) {
        CompletableFuture.runAsync(() -> {
            try {
                assetNotClose();
                for (K key : keys) {
                    V value = cacheLoader.load(key);
                    if (replaceExistingValues || !containsEntry(key)) {
                        put(key, value);
                    }
                }
                completionListener.onCompletion();
            } catch (Exception ex) {
                completionListener.onException(ex);
            }
        });

    }

    @Override
    public void put(K key, V value) {
        assetNotClose();
        assetNotNull(key);
        assetNotNull(value);
        putEntryAndWrite(key, value);
    }

    private void putEntryAndWrite(K key, V value) {
        try {
            putEntry(key, value);
            if (configuration.isWriteThrough()) {
                cacheWriter.write(ExpireEntry.of(key, value));
            }
            publishCreatedEvent(key, value);
        } catch (Exception ex) {
            throw new CacheException();
        }
    }

    private void putEntryAndReplace(K key, V value, V oldValue) {
        try {
            putEntry(key, value);
            if (configuration.isWriteThrough()) {
                cacheWriter.write(ExpireEntry.of(key, value));
            }
            publishUpdatedEvent(key, value, oldValue);
        } catch (Exception ex) {
            throw new CacheException();
        }
    }

    protected abstract void putEntry(K key, V value);

    @Override
    public V getAndPut(K key, V value) {
        V oldValue = get(key);
        put(key, value);
        return oldValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        assetNotNull(map);
        map.entrySet().forEach(entry -> {
            put(entry.getKey(), entry.getValue());
        });
    }

    @Override
    public boolean putIfAbsent(K key, V value) {
        if (!containsKey(key)) {
            put(key, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(K key) {
        assetNotClose();
        assetNotNull(key);
        return removeEntry(key);
    }

    private boolean removeEntry(K key) {
        if (containsEntry(key)) {
            deleteEntry(key);
            if (configuration.isWriteThrough()) {
                cacheWriter.delete(key);
            }
            publishRemovedEvent(key, null);
            return true;
        }
        return false;
    }

    protected abstract boolean deleteEntry(K key);

    @Override
    public boolean remove(K key, V oldValue) {
        V currentValue = get(key);
        if (Objects.equals(currentValue, oldValue)) {
            return remove(key);
        }
        return false;
    }

    @Override
    public V getAndRemove(K key) {
        V oldValue = get(key);
        removeEntry(key);
        return oldValue;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        V currentValue = get(key);
        if (!Objects.equals(currentValue, oldValue)) {
            return false;
        }
        putEntryAndReplace(key, newValue, currentValue);
        return true;
    }

    @Override
    public boolean replace(K key, V value) {
        assetNotNull(key);
        assetNotNull(value);
        assetNotClose();
        if (!containsEntry(key)) {
            return false;
        }
        V currentValue = get(key);
        putEntryAndReplace(key, value, currentValue);
        return true;
    }

    @Override
    public V getAndReplace(K key, V value) {
        assetNotNull(key);
        assetNotNull(value);
        assetNotClose();
        if (!containsEntry(key)) {
            return null;
        }
        ExpireEntry<K, V> oldEntry = getEntry(key);
        V oldValue = getValue(oldEntry);
        putEntryAndReplace(key, value, oldValue);
        return oldValue;
    }

    @Override
    public void removeAll(Set<? extends K> keys) {
        assetNotClose();
        for (K key : keys) {
            assetNotNull(key);
            removeEntry(key);
        }
    }

    @Override
    public void removeAll() {
        assetNotClose();
        removeAllEntry();
        if (configuration.isWriteThrough()) {
            cacheWriter.deleteAll(getAllKeys());
        }
    }

    /**
     * get cache all keys
     *
     * @return
     */
    protected abstract Collection<K> getAllKeys();

    protected abstract void removeAllEntry();

    @Override
    public void clear() {
        assetNotClose();
        removeAllEntry();
        if (configuration.isWriteThrough()) {
            cacheWriter.deleteAll(getAllKeys());
        }
    }

    @Override
    public <C extends Configuration<K, V>> C getConfiguration(Class<C> clazz) {
        if (!Configuration.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("the class is not assigned from [javax.cache.configuration.Configuration.class]");
        }
        return clazz.cast(configuration);
    }

    @Override
    public <T> T invoke(K key, EntryProcessor<K, V, T> entryProcessor, Object... arguments) throws EntryProcessorException {
        return entryProcessor.process(MutableEntryAdapter.of(key, this), arguments);
    }

    @Override
    public <T> Map<K, EntryProcessorResult<T>> invokeAll(Set<? extends K> keys, EntryProcessor<K, V, T> entryProcessor,
                                                         Object... arguments) {
        Map<K, EntryProcessorResult<T>> mapResult = new HashMap<>();
        for (K key : keys) {
            T result = entryProcessor.process(MutableEntryAdapter.of(key, this), arguments);
            mapResult.put(key, () -> result);
        }
        return mapResult;
    }

    @Override
    public String getName() {
        return this.cacheName;
    }

    @Override
    public CacheManager getCacheManager() {
        return this.cacheManager;
    }

    @Override
    public void registerCacheEntryListener(CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {
        publisher.registerListener(ConditionalCacheEntryListenerAdapter.of(cacheEntryListenerConfiguration));
    }

    @Override
    public void deregisterCacheEntryListener(CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {
        publisher.deregisterListener(ConditionalCacheEntryListenerAdapter.of(cacheEntryListenerConfiguration));
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        assetNotClose();
        List<Entry<K, V>> result = new LinkedList<>();
        for(K key : getAllKeys()){
            V value = get(key);
            result.add(ExpireEntry.of(key, value));
        }
        return result.iterator();
    }

    /**
     * 发布新增缓存事件
     *
     * @param key   缓存key
     * @param value 缓存value
     */
    private void publishCreatedEvent(K key, V value) {
        publisher.publishEvent(GenericCacheEntryEvent.createdEvent(this, key, value));
    }

    /**
     * 发布更新缓存事件
     *
     * @param key      缓存key
     * @param value    需要更新缓存的目标值
     * @param oldValue 缓存中已有的老值
     */
    private void publishUpdatedEvent(K key, V value, V oldValue) {
        publisher.publishEvent(GenericCacheEntryEvent.updatedEvent(this, key, value, oldValue));
    }

    /**
     * 发布删除缓存事件
     *
     * @param key 缓存key
     * @param oldValue 缓存中已有的老值
     */
    private void publishRemovedEvent(K key, V oldValue) {
        publisher.publishEvent(GenericCacheEntryEvent.removedEvent(this, key, oldValue));
    }

    /**
     * 发布缓存过期事件
     *
     * @param key 缓存key
     * @param oldValue 缓存中已有的老值
     */
    private void publishExpiredEvent(K key, V oldValue) {
        publisher.publishEvent(GenericCacheEntryEvent.expiredEvent(this, key, oldValue));
    }

}