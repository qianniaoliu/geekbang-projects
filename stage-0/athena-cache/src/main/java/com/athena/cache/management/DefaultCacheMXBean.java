/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache.management;

import javax.cache.configuration.CompleteConfiguration;
import javax.cache.management.CacheMXBean;

/**
 * @author shenlong
 */
public class DefaultCacheMXBean implements CacheMXBean {

    private final CompleteConfiguration configuration;

    public DefaultCacheMXBean(CompleteConfiguration configuration) {this.configuration = configuration;}

    @Override
    public String getKeyType() {
        return this.configuration.getKeyType().getSimpleName();
    }

    @Override
    public String getValueType() {
        return this.configuration.getValueType().getSimpleName();
    }

    @Override
    public boolean isReadThrough() {
        return this.configuration.isReadThrough();
    }

    @Override
    public boolean isWriteThrough() {
        return this.configuration.isWriteThrough();
    }

    @Override
    public boolean isStoreByValue() {
        return this.configuration.isStoreByValue();
    }

    @Override
    public boolean isStatisticsEnabled() {
        return this.configuration.isStatisticsEnabled();
    }

    @Override
    public boolean isManagementEnabled() {
        return this.configuration.isManagementEnabled();
    }
}