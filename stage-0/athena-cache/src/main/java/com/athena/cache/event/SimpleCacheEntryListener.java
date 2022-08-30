/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache.event;

import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListener;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;

/**
 * @author shenlong
 * @version SimpleCacheEntryListener.java, v 0.1 2022年03月16日 3:05 下午 shenlong
 */
public class SimpleCacheEntryListener implements CacheEntryListenerConfiguration<String, String>,
        CacheEntryCreatedListener<String,String>, CacheEntryUpdatedListener<String, String>,
        CacheEntryRemovedListener<String, String>, CacheEntryExpiredListener<String, String> {
    @Override
    public Factory<CacheEntryListener<? super String, ? super String>> getCacheEntryListenerFactory() {
        return () -> this;
    }

    @Override
    public boolean isOldValueRequired() {
        return false;
    }

    @Override
    public Factory<CacheEntryEventFilter<? super String, ? super String>> getCacheEntryEventFilterFactory() {
        return () -> (event -> true);
    }

    @Override
    public boolean isSynchronous() {
        return false;
    }

    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends String, ? extends String>> cacheEntryEvents)
            throws CacheEntryListenerException {
        cacheEntryEvents.forEach(cacheEntryEvent -> {
            System.out.println("listen created event:" + cacheEntryEvent);
        });
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends String, ? extends String>> cacheEntryEvents)
            throws CacheEntryListenerException {
        cacheEntryEvents.forEach(cacheEntryEvent -> {
            System.out.println("listen expired event:" + cacheEntryEvent);
        });
    }

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends String, ? extends String>> cacheEntryEvents)
            throws CacheEntryListenerException {
        cacheEntryEvents.forEach(cacheEntryEvent -> {
            System.out.println("listen removed event:" + cacheEntryEvent);
        });
    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends String, ? extends String>> cacheEntryEvents)
            throws CacheEntryListenerException {
        cacheEntryEvents.forEach(cacheEntryEvent -> {
            System.out.println("listen updated event:" + cacheEntryEvent);
        });
    }
}