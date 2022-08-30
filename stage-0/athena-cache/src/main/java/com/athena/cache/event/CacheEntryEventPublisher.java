/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache.event;

import com.athena.cache.util.AssetUtils;

import javax.cache.event.CacheEntryEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlong
 */
public class CacheEntryEventPublisher {

    /**
     * 事件监听器列表，用于事件发布时迭代
     */
    private List<ConditionalCacheEntryListener> listeners = new ArrayList<>();

    /**
     * 注册事件监听器
     * @param listener 事件监听器
     */
    public void registerListener(ConditionalCacheEntryListener listener) {
        AssetUtils.assetNotNull(listener);
        listeners.add(listener);
    }

    /**
     * 注销事件监听器
     * @param listener 事件监听器
     */
    public void deregisterListener(ConditionalCacheEntryListener listener) {
        AssetUtils.assetNotNull(listener);
        listeners.remove(listener);
    }

    /**
     * 发布事件内容
     * @param event 事件内容
     */
    public void publishEvent(CacheEntryEvent event) {
        listeners.stream()
                .filter(listener -> listener.supports(event))
                .forEach(listener -> listener.onEvent(event));
    }
}