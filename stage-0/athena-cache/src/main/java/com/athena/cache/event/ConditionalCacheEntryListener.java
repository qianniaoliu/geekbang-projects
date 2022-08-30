/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache.event;

import javax.cache.event.CacheEntryEvent;
import java.util.EventListener;

/**
 * @author shenlong
 */
public interface ConditionalCacheEntryListener<K, V> extends EventListener {

    boolean supports(CacheEntryEvent<K, V> event);

    void onEvent(CacheEntryEvent<K, V> event);
}