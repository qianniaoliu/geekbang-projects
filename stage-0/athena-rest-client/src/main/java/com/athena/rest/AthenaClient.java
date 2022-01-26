/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.rest;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

/**
 * @author shenlong
 * @version AthenaClient.java, v 0.1 2021��12��06�� 4:32 ���� shenlong
 */
public class AthenaClient implements Client {
    @Override
    public void close() {

    }

    @Override
    public WebTarget target(String uri) {
        return target(URI.create(uri));
    }

    @Override
    public WebTarget target(URI uri) {
        return target(UriBuilder.fromUri(uri));
    }

    @Override
    public WebTarget target(UriBuilder uriBuilder) {
        return new AthenaWebTarget(uriBuilder);
    }

    @Override
    public WebTarget target(Link link) {
        return null;
    }

    @Override
    public Builder invocation(Link link) {
        return null;
    }

    @Override
    public SSLContext getSslContext() {
        return null;
    }

    @Override
    public HostnameVerifier getHostnameVerifier() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public Client property(String name, Object value) {
        return null;
    }

    @Override
    public Client register(Class<?> componentClass) {
        return null;
    }

    @Override
    public Client register(Class<?> componentClass, int priority) {
        return null;
    }

    @Override
    public Client register(Class<?> componentClass, Class<?>... contracts) {
        return null;
    }

    @Override
    public Client register(Class<?> componentClass, Map<Class<?>, Integer> contracts) {
        return null;
    }

    @Override
    public Client register(Object component) {
        return null;
    }

    @Override
    public Client register(Object component, int priority) {
        return null;
    }

    @Override
    public Client register(Object component, Class<?>... contracts) {
        return null;
    }

    @Override
    public Client register(Object component, Map<Class<?>, Integer> contracts) {
        return null;
    }
}