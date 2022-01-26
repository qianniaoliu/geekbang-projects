/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.rest;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configuration;
import java.security.KeyStore;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author shenlong
 * @version AthenaClientBuilder.java, v 0.1 2021��12��06�� 4:25 ���� shenlong
 */
public class AthenaClientBuilder extends ClientBuilder {
    @Override
    public ClientBuilder withConfig(Configuration config) {
        return null;
    }

    @Override
    public ClientBuilder sslContext(SSLContext sslContext) {
        return null;
    }

    @Override
    public ClientBuilder keyStore(KeyStore keyStore, char[] password) {
        return null;
    }

    @Override
    public ClientBuilder trustStore(KeyStore trustStore) {
        return null;
    }

    @Override
    public ClientBuilder hostnameVerifier(HostnameVerifier verifier) {
        return null;
    }

    @Override
    public ClientBuilder executorService(ExecutorService executorService) {
        return null;
    }

    @Override
    public ClientBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        return null;
    }

    @Override
    public ClientBuilder connectTimeout(long timeout, TimeUnit unit) {
        return null;
    }

    @Override
    public ClientBuilder readTimeout(long timeout, TimeUnit unit) {
        return null;
    }

    @Override
    public Client build() {
        return new AthenaClient();
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public ClientBuilder property(String name, Object value) {
        return null;
    }

    @Override
    public ClientBuilder register(Class<?> componentClass) {
        return null;
    }

    @Override
    public ClientBuilder register(Class<?> componentClass, int priority) {
        return null;
    }

    @Override
    public ClientBuilder register(Class<?> componentClass, Class<?>... contracts) {
        return null;
    }

    @Override
    public ClientBuilder register(Class<?> componentClass, Map<Class<?>, Integer> contracts) {
        return null;
    }

    @Override
    public ClientBuilder register(Object component) {
        return null;
    }

    @Override
    public ClientBuilder register(Object component, int priority) {
        return null;
    }

    @Override
    public ClientBuilder register(Object component, Class<?>... contracts) {
        return null;
    }

    @Override
    public ClientBuilder register(Object component, Map<Class<?>, Integer> contracts) {
        return null;
    }
}