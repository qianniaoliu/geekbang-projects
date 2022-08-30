/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author shenlong
 */
public class FileCacheLoader<K, V> implements CacheLoader<K, V> {

    private final static String FILE_DATABASE_PATH = "META-INF/file-database.properties";

    private final Properties properties;

    public FileCacheLoader() {
        this.properties = new Properties();
        try {
            this.properties.load(getClass().getClassLoader().getResourceAsStream(FILE_DATABASE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public V load(K key) throws CacheLoaderException {
        return (V) this.properties.getProperty(String.valueOf(key));
    }

    @Override
    public Map<K, V> loadAll(Iterable<? extends K> keys) throws CacheLoaderException {
        Map<K, V> result = new HashMap<>();
        for (K key : keys) {
            result.put(key, load(key));
        }
        return result;
    }
}