/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.service;

/**
 * @author shenlong
 * @version EchoServiceImpl.java, v 0.1 2021年10月11日 2:42 下午 shenlong
 */
public class EchoServiceImpl implements EchoService{
    @Override
    public String echo(String message) {
        return "Hello " + message;
    }
}