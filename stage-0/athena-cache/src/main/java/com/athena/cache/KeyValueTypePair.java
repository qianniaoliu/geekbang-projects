/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package com.athena.cache;

import lombok.Getter;

import java.util.Objects;

/**
 * @author shenlong
 */
@Getter
public class KeyValueTypePair<K, V> {

    private Class<K> keyClazz;

    private Class<V> valueClazz;

    public KeyValueTypePair(Class<K> keyClazz, Class<V> valueClazz) {
        this.keyClazz = keyClazz;
        this.valueClazz = valueClazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        KeyValueTypePair<?, ?> that = (KeyValueTypePair<?, ?>) o;
        return Objects.equals(keyClazz, that.keyClazz) && Objects.equals(valueClazz, that.valueClazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyClazz, valueClazz);
    }

    public static <K, V> KeyValueTypePair<K, V> of(Class<K> keyClazz, Class<V> valueClazz) {
        return new KeyValueTypePair<>(keyClazz, valueClazz);
    }
}