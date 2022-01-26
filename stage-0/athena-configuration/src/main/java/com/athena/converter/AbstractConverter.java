/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.converter;

import org.eclipse.microprofile.config.spi.Converter;

import java.util.Objects;

/**
 * @author shenlong
 */
public abstract class AbstractConverter<T> implements Converter<T> {

    @Override
    public T convert(String value) throws IllegalArgumentException, NullPointerException {
        if(Objects.isNull(value)){
            throw new IllegalArgumentException("value is null");
        }
        try {
            return doConvert(value);
        }catch (Exception ex){
            throw new IllegalStateException("convert error", ex);
        }

    }

    protected abstract T doConvert(String value);
}