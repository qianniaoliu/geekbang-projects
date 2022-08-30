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
     * �¼��������б������¼�����ʱ����
     */
    private List<ConditionalCacheEntryListener> listeners = new ArrayList<>();

    /**
     * ע���¼�������
     * @param listener �¼�������
     */
    public void registerListener(ConditionalCacheEntryListener listener) {
        AssetUtils.assetNotNull(listener);
        listeners.add(listener);
    }

    /**
     * ע���¼�������
     * @param listener �¼�������
     */
    public void deregisterListener(ConditionalCacheEntryListener listener) {
        AssetUtils.assetNotNull(listener);
        listeners.remove(listener);
    }

    /**
     * �����¼�����
     * @param event �¼�����
     */
    public void publishEvent(CacheEntryEvent event) {
        listeners.stream()
                .filter(listener -> listener.supports(event))
                .forEach(listener -> listener.onEvent(event));
    }
}