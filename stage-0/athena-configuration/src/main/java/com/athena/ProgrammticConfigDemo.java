/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

/**
 * @author shenlong
 */
public class ProgrammticConfigDemo {

    public static void main(String[] args) {
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