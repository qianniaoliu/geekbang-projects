/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.converter;

import org.eclipse.microprofile.config.spi.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlong
 */
public class Converters implements Iterable<Converter> {

    private final Map<Class, Converter> convertMaps = new ConcurrentHashMap<>();

    public void addDiscoveredConverter() {
        ServiceLoader<Converter> converterServiceLoader = ServiceLoader.load(Converter.class);
        converterServiceLoader.forEach(converter -> {
            convertMaps.put(getConvertType(converter.getClass()), converter);
        });
    }

    public void addConverter(Converter converter) {
        Class convertType = getConvertType(converter.getClass());
        convertMaps.put(getConvertType(converter.getClass()), converter);
    }

    private static Class getConvertType(Class<? extends Converter> clazz) {
        Type superType = clazz.getGenericSuperclass();
        if (superType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superType;
            Type[] argTypes = parameterizedType.getActualTypeArguments();
            if (argTypes != null && argTypes.length == 1) {
                return (Class) argTypes[0];
            }
        }
        return null;
    }

    public Converter getConverter(Class<?> clazzType){
        return convertMaps.get(clazzType);
    }

    @Override
    public Iterator<Converter> iterator() {
        return convertMaps.values().iterator();
    }
}