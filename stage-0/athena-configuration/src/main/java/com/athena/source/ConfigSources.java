/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.source;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author shenlong
 */
public class ConfigSources implements Iterable<ConfigSource>{

    private final List<ConfigSource> configSources = new ArrayList<>();

    private final ClassLoader classLoader;

    public ConfigSources(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void addDiscoveredConfigSource(){
        ServiceLoader<ConfigSource> configSourceServiceLoader = ServiceLoader.load(ConfigSource.class);
        configSourceServiceLoader.forEach(configSource -> {
            configSources.add(configSource);
        });
    }


    public void addConfigSource(ConfigSource configSource){
        configSources.add(configSource);
    }


    @Override
    public Iterator<ConfigSource> iterator() {
        return configSources.iterator();
    }
}