/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import org.apache.commons.lang3.StringUtils;

import javax.cache.CacheManager;
import javax.cache.configuration.OptionalFeature;
import javax.cache.spi.CachingProvider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Objects;
import java.util.Properties;

/**
 * @author shenlong
 */
public class ConfigurableCachingProvider implements CachingProvider {

    private final static String DEFAULT_CACHE_MANAGE_PREFIX      = "com.athena.cache.manager.mapping.";
    private final static String DEFAULT_CACHE_MANAGE_CONFIG_FILE = "META-INF/default-cache-manage-config.properties";

    private Properties defaultProperties;

    @Override
    public CacheManager getCacheManager(URI uri, ClassLoader classLoader, Properties properties) {
        return createCacheManager(uri, properties, classLoader);
    }

    @Override
    public ClassLoader getDefaultClassLoader() {
        return this.getClass().getClassLoader();
    }

    @Override
    public URI getDefaultURI() {
        return URI.create("in-memory://127.0.0.1");
    }

    @Override
    public Properties getDefaultProperties() {
        if (Objects.isNull(defaultProperties)) {
            defaultProperties = new Properties();
            try (InputStream inputStream = getDefaultClassLoader().getResourceAsStream(DEFAULT_CACHE_MANAGE_CONFIG_FILE)) {
                defaultProperties.load(inputStream);
            } catch (IOException ex) {
            }
        }
        return defaultProperties;
    }

    @Override
    public CacheManager getCacheManager(URI uri, ClassLoader classLoader) {
        return createCacheManager(uri, getDefaultProperties(), classLoader);
    }

    @Override
    public CacheManager getCacheManager() {
        return createCacheManager(getDefaultURI(), getDefaultProperties(), getDefaultClassLoader());
    }

    @Override
    public void close() {

    }

    @Override
    public void close(ClassLoader classLoader) {

    }

    @Override
    public void close(URI uri, ClassLoader classLoader) {

    }

    @Override
    public boolean isSupported(OptionalFeature optionalFeature) {
        return false;
    }

    private CacheManager createCacheManager(URI uri, Properties properties, ClassLoader classLoader) {
        String className = getClassName(uri, properties);
        try {
            Class clazz = classLoader.loadClass(className);
            if (!AbstractCacheManager.class.isAssignableFrom(clazz)) {
                throw new IllegalStateException(
                        "this class [" + className + "] is not assignable form " + AbstractCacheManager.class.getSimpleName());
            }
            Constructor constructor = clazz.getConstructor(CachingProvider.class, URI.class, ClassLoader.class, Properties.class);
            constructor.setAccessible(true);
            return (CacheManager) constructor.newInstance(this, uri, classLoader, properties);
        } catch (Exception ex) {
        }
        return null;
    }

    private String getClassName(URI uri, Properties properties) {
        String scheme = uri.getScheme();
        String fullClassNameKey = DEFAULT_CACHE_MANAGE_PREFIX + scheme;
        String className = properties.getProperty(fullClassNameKey);
        if (StringUtils.isBlank(className)) {
            throw new IllegalStateException("this scheme:" + scheme + " is not exist");
        }
        return className;
    }
}