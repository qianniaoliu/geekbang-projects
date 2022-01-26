/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena;

import com.athena.converter.Converters;
import com.athena.source.ConfigSources;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author shenlong
 */
public class DefaultConfigBuilder implements ConfigBuilder {

    private ConfigSources configSources = new ConfigSources(getClass().getClassLoader());

    private Converters converters = new Converters();


    @Override
    public ConfigBuilder addDefaultSources() {
        return this;
    }

    @Override
    public ConfigBuilder addDiscoveredSources() {
        configSources.addDiscoveredConfigSource();
        return this;
    }

    @Override
    public ConfigBuilder addDiscoveredConverters() {
        converters.addDiscoveredConverter();
        return this;
    }

    @Override
    public ConfigBuilder forClassLoader(ClassLoader classLoader) {
        return null;
    }

    @Override
    public ConfigBuilder withSources(ConfigSource... configSources) {
        return null;
    }

    @Override
    public ConfigBuilder withConverters(Converter<?>... converters) {
        return null;
    }

    @Override
    public <T> ConfigBuilder withConverter(Class<T> aClass, int i, Converter<T> converter) {
        return null;
    }

    @Override
    public Config build() {
        return new DefaultConfig(configSources, converters);
    }
}