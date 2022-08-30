/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import com.athena.cache.event.SimpleCacheEntryListener;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import java.net.URI;

/**
 * @author shenlong
 */
public class CacheTest {


    @Test
    public void testCacheInMemory(){
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager(URI.create("in-memory://127.0.0.1"), this.getClass().getClassLoader());
        MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
        configuration.setTypes(String.class, String.class);
        Cache<String, String> cache = cacheManager.createCache("inMemoryCache", configuration);
        cache.registerCacheEntryListener(new SimpleCacheEntryListener());
        cache.put("userName", "athena");

        //Êä³ö
        System.out.println(cache.get("userName"));
    }

}