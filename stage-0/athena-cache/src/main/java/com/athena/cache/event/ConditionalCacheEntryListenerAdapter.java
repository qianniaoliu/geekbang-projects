/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryListener;
import javax.cache.event.EventType;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author shenlong
 * @version ConditionalCacheEntryListenerAdapter.java, v 0.1 2022年03月01日 4:52 下午 shenlong
 */
public class ConditionalCacheEntryListenerAdapter<K, V> implements ConditionalCacheEntryListener<K, V> {

    private final static Map<String, EventType> SUPPORT_METHOD_EVENT_MAPPING = new HashMap<>();

    private final CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration;

    private final CacheEntryListener<? super K, ? super V> listener;

    private final CacheEntryEventFilter<? super K, ? super V> filter;

    private final Map<EventType, Method> eventTypeMethodMap;

    static {
        SUPPORT_METHOD_EVENT_MAPPING.put("onCreated", EventType.CREATED);
        SUPPORT_METHOD_EVENT_MAPPING.put("onExpired", EventType.EXPIRED);
        SUPPORT_METHOD_EVENT_MAPPING.put("onRemoved", EventType.REMOVED);
        SUPPORT_METHOD_EVENT_MAPPING.put("onUpdated", EventType.UPDATED);
    }

    public ConditionalCacheEntryListenerAdapter(
            CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {
        this.cacheEntryListenerConfiguration = cacheEntryListenerConfiguration;
        this.listener = cacheEntryListenerConfiguration.getCacheEntryListenerFactory().create();
        this.filter = getCacheEntryEventFilter(cacheEntryListenerConfiguration);
        this.eventTypeMethodMap = getMethodEventType();
    }

    @Override
    public boolean supports(CacheEntryEvent event) {
        return this.eventTypeMethodMap.containsKey(event.getEventType()) && this.filter.evaluate(event);
    }

    @Override
    public void onEvent(CacheEntryEvent event) {
        Method method = this.eventTypeMethodMap.get(event.getEventType());
        method.setAccessible(true);
        try {
            method.invoke(this.listener, List.of(event));
        } catch (Exception e) {
        }
    }

    private CacheEntryEventFilter<? super K, ? super V> getCacheEntryEventFilter(
            CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {
        return Optional.ofNullable(cacheEntryListenerConfiguration.getCacheEntryEventFilterFactory().create())
                .orElse(event -> true);
    }

    private Map<EventType, Method> getMethodEventType() {
        Class listenerClass = this.listener.getClass();
        return SUPPORT_METHOD_EVENT_MAPPING.entrySet().stream().map(entry -> {
            try {
                Method method = listenerClass.getMethod(entry.getKey(), Iterable.class);
                return Optional.ofNullable(method)
                        .map(item -> new MethodEventType(item, entry.getValue()))
                        .orElse(null);
            } catch (NoSuchMethodException ex) {
                // ignore NoSuchMethodException
            }
            return null;
        }).filter(Objects::nonNull)
                .collect(Collectors.toMap(MethodEventType::getEventType, MethodEventType::getMethod));
    }


    public static <K,V> ConditionalCacheEntryListenerAdapter<K, V> of(CacheEntryListenerConfiguration<K, V> configuration){
        return new ConditionalCacheEntryListenerAdapter(configuration);
    }

    @AllArgsConstructor
    @Getter
    private class MethodEventType {
        private Method    method;
        private EventType eventType;
    }
}