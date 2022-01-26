/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.servlet;

import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author shenlong
 */
public class AthenaServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConfigBuilder configBuilder = ConfigProviderResolver.instance().getBuilder();
        configBuilder.addDiscoveredSources();
        configBuilder.addDiscoveredConverters();
        configBuilder.build();
        System.out.println("auto registry configuration");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}