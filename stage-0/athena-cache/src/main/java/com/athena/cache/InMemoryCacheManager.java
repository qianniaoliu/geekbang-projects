/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import com.athena.cache.event.CacheEntryEventPublisher;
import com.athena.cache.event.ConditionalCacheEntryListenerAdapter;

import javax.cache.Cache;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.util.Properties;

/**
 * @author shenlong
 * @version InMemoryCacheManager.java, v 0.1 2022年03月15日 5:01 下午 shenlong
 */
public class InMemoryCacheManager extends AbstractCacheManager{

    public InMemoryCacheManager(CachingProvider cachingProvider, URI uri, ClassLoader classLoader,
                                Properties properties) {
        super(cachingProvider, uri, classLoader, properties);
    }

    @Override
    protected <K, V, C extends Configuration<K, V>> Cache doCreateCache(String cacheName, C configuration) {
        return new InMemoryCache(new FileCacheLoader(), new FileCacheWriter(), (CompleteConfiguration) configuration, this, cacheName);
    }
}