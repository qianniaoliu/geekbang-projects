/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.junit.Test;

/**
 * @author shenlong
 */
public class ProgrammticConfigDemo {

    @Test
    public void testConfig() {
        ConfigBuilder configBuilder = ConfigProviderResolver.instance().getBuilder();
        configBuilder.addDiscoveredSources();
        configBuilder.addDiscoveredConverters();
        Config config = configBuilder.build();
        String applicationName = config.getValue("application.name", String.class);
        Boolean cacheEnable = config.getValue("cache.enable", Boolean.class);
        Integer timeout = config.getValue("cache.timeout", Integer.class);
        System.out.println(applicationName);
        System.out.println(cacheEnable);
        System.out.println(timeout);
    }
}