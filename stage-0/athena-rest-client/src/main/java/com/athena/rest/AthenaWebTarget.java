/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.rest;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlong
 * @version AthenaWebTarget.java, v 0.1 2021年12月06日 8:18 下午 shenlong
 */
public class AthenaWebTarget implements WebTarget {

    private final UriBuilder uriBuilder;

    private Map<String, Object> templateValues = new HashMap<>();

    public AthenaWebTarget(UriBuilder uriBuilder) {this.uriBuilder = uriBuilder;}

    @Override
    public URI getUri() {
        return this.uriBuilder.build();
    }

    @Override
    public UriBuilder getUriBuilder() {
        return this.uriBuilder;
    }

    @Override
    public WebTarget path(String path) {
        this.uriBuilder.path(path);
        return this;
    }

    @Override
    public WebTarget resolveTemplate(String name, Object value) {
        return this;
    }

    @Override
    public WebTarget resolveTemplate(String name, Object value, boolean encodeSlashInPath) {
        return this;
    }

    @Override
    public WebTarget resolveTemplateFromEncoded(String name, Object value) {
        return this;
    }

    @Override
    public WebTarget resolveTemplates(Map<String, Object> templateValues) {
        this.templateValues = templateValues;
        return this;
    }

    @Override
    public WebTarget resolveTemplates(Map<String, Object> templateValues, boolean encodeSlashInPath) {
        return this;
    }

    @Override
    public WebTarget resolveTemplatesFromEncoded(Map<String, Object> templateValues) {
        return this;
    }

    @Override
    public WebTarget matrixParam(String name, Object... values) {
        this.uriBuilder.matrixParam(name, values);
        return this;
    }

    @Override
    public WebTarget queryParam(String name, Object... values) {
        this.uriBuilder.queryParam(name, values);
        return this;
    }

    @Override
    public Builder request() {
        return new AthenaBuilder(uriBuilder);
    }

    @Override
    public Builder request(String... acceptedResponseTypes) {
        return null;
    }

    @Override
    public Builder request(MediaType... acceptedResponseTypes) {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public WebTarget property(String name, Object value) {
        return null;
    }

    @Override
    public WebTarget register(Class<?> componentClass) {
        return null;
    }

    @Override
    public WebTarget register(Class<?> componentClass, int priority) {
        return null;
    }

    @Override
    public WebTarget register(Class<?> componentClass, Class<?>... contracts) {
        return null;
    }

    @Override
    public WebTarget register(Class<?> componentClass, Map<Class<?>, Integer> contracts) {
        return null;
    }

    @Override
    public WebTarget register(Object component) {
        return null;
    }

    @Override
    public WebTarget register(Object component, int priority) {
        return null;
    }

    @Override
    public WebTarget register(Object component, Class<?>... contracts) {
        return null;
    }

    @Override
    public WebTarget register(Object component, Map<Class<?>, Integer> contracts) {
        return null;
    }
}