/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.converter;

/**
 * @author shenlong
 */
public class IntegerConverter extends AbstractConverter<Integer>{
    @Override
    protected Integer doConvert(String value) {
        return Integer.valueOf(value);
    }
}