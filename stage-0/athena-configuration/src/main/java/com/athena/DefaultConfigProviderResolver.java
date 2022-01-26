/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author shenlong
 */
public class DefaultConfigProviderResolver extends ConfigProviderResolver {

    private Map<ClassLoader, Config> configMap = new ConcurrentHashMap<>();


    @Override
    public Config getConfig() {
        return configMap.get(Thread.currentThread().getContextClassLoader());
    }

    @Override
    public Config getConfig(ClassLoader classLoader) {
        return configMap.get(classLoader);
    }

    @Override
    public ConfigBuilder getBuilder() {
        return new DefaultConfigBuilder();
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {
        configMap.put(classLoader, config);
    }

    @Override
    public void releaseConfig(Config config) {
        List<ClassLoader> targetKeys = configMap.entrySet().stream()
                .filter(configEntry -> Objects.equals(config, configEntry.getValue()))
                .map(Entry::getKey)
                .collect(Collectors.toList());
        targetKeys.forEach(configMap::remove);
    }
}