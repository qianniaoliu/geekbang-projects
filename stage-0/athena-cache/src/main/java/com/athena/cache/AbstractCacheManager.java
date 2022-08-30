/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import com.athena.cache.management.DefaultCacheMXBean;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.management.CacheMXBean;
import javax.cache.spi.CachingProvider;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlong
 */
public abstract class AbstractCacheManager implements CacheManager {

    private final CachingProvider cachingProvider;

    private final URI uri;

    private final ClassLoader classLoader;

    private final Properties properties;

    private final Map<String, Map<KeyValueTypePair, Cache>> cacheMapping = new ConcurrentHashMap<>();

    private CompleteConfiguration configuration;

    public AbstractCacheManager(CachingProvider cachingProvider, URI uri, ClassLoader classLoader, Properties properties) {
        this.cachingProvider = cachingProvider;
        this.uri = uri;
        this.classLoader = classLoader;
        this.properties = properties;
    }

    @Override
    public CachingProvider getCachingProvider() {
        return this.cachingProvider;
    }

    @Override
    public URI getURI() {
        return this.uri;
    }

    @Override
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    @Override
    public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(String cacheName, C configuration)
            throws IllegalArgumentException {
        Map<KeyValueTypePair, Cache> cacheMap = cacheMapping.getOrDefault(cacheName, new HashMap<>());
        return cacheMap.computeIfAbsent(new KeyValueTypePair(configuration.getKeyType(), configuration.getValueType()),
                key -> doCreateCache(cacheName, configuration));
    }

    protected abstract <K, V, C extends Configuration<K, V>> Cache doCreateCache(String cacheName, C configuration);

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName, Class<K> keyType, Class<V> valueType) {
        Map<KeyValueTypePair, Cache> cacheMap = cacheMapping.getOrDefault(cacheName, new HashMap<>());
        return cacheMap.get(KeyValueTypePair.of(keyType, valueType));
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) {
        return getCache(cacheName, (Class<K>) Object.class, (Class<V>) Object.class);
    }

    @Override
    public Iterable<String> getCacheNames() {
        return cacheMapping.keySet();
    }

    @Override
    public void destroyCache(String cacheName) {
        cacheMapping.remove(cacheName);
    }

    @Override
    public void enableManagement(String cacheName, boolean enabled) {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        Map<KeyValueTypePair, Cache> cacheMap = cacheMapping.getOrDefault(cacheName, new HashMap<>());
        cacheMap.entrySet().forEach(entry -> {
            CacheMXBean cacheMXBean = new DefaultCacheMXBean(configuration);
            registerCacheMBean(cacheMXBean, cacheName, "CacheConfiguration", mBeanServer);
        });
    }

    private void registerCacheMBean(CacheMXBean cacheMXBean, String cacheName, String typeName, MBeanServer mBeanServer) {
        if (!cacheMXBean.isManagementEnabled()) {
            return;
        }
        ObjectName objectName = createObjectName(cacheName, typeName);
        if (Objects.nonNull(objectName) && !mBeanServer.isRegistered(objectName)) {
            try {
                mBeanServer.registerMBean(cacheMXBean, objectName);
            } catch (Exception e) {
            }
        }
    }

    private ObjectName createObjectName(String cacheName, String typeName) {
        try {
            Hashtable<String, String> params = new Hashtable<>();
            params.put("url", this.uri.toString());
            params.put("cacheName", cacheName);
            params.put("type", typeName);
            ObjectName objectName = new ObjectName("javax.cache", params);
            return objectName;
        } catch (MalformedObjectNameException e) {
        }
        return null;
    }

    @Override
    public void enableStatistics(String cacheName, boolean enabled) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }
}