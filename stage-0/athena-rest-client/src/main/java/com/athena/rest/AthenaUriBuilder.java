/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.rest;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author shenlong
 * @version AthenaUriBuilder.java, v 0.1 2021年12月06日 8:18 下午 shenlong
 */
public class AthenaUriBuilder extends UriBuilder {

    private URI uri;

    private String uriTemplate;

    private String scheme;

    private String host;

    private int port;

    private MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();

    @Override
    public UriBuilder clone() {
        return null;
    }

    @Override
    public UriBuilder uri(URI uri) {
        this.uri = uri;
        return this;
    }

    @Override
    public UriBuilder uri(String uriTemplate) {
        this.uriTemplate = uriTemplate;
        return this;
    }

    @Override
    public UriBuilder scheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    @Override
    public UriBuilder schemeSpecificPart(String ssp) {
        return this;
    }

    @Override
    public UriBuilder userInfo(String ui) {
        return this;
    }

    @Override
    public UriBuilder host(String host) {
        this.host = host;
        return this;
    }

    @Override
    public UriBuilder port(int port) {
        this.port = port;
        return this;
    }

    @Override
    public UriBuilder replacePath(String path) {
        return null;
    }

    @Override
    public UriBuilder path(String path) {
        return null;
    }

    @Override
    public UriBuilder path(Class resource) {
        return null;
    }

    @Override
    public UriBuilder path(Class resource, String method) {
        return null;
    }

    @Override
    public UriBuilder path(Method method) {
        return null;
    }

    @Override
    public UriBuilder segment(String... segments) {
        return null;
    }

    @Override
    public UriBuilder replaceMatrix(String matrix) {
        return null;
    }

    @Override
    public UriBuilder matrixParam(String name, Object... values) {
        return null;
    }

    @Override
    public UriBuilder replaceMatrixParam(String name, Object... values) {
        return null;
    }

    @Override
    public UriBuilder replaceQuery(String query) {
        return null;
    }

    @Override
    public UriBuilder queryParam(String name, Object... values) {
        this.queryParams.put(name, asList(values));
        return this;
    }

    String[] of(Object... values) {
        return Stream.of(values).toArray(String[]::new);
    }
    private List<String> asList(Object... values) {
        return Arrays.asList(of(values));
    }

    @Override
    public UriBuilder replaceQueryParam(String name, Object... values) {
        return null;
    }

    @Override
    public UriBuilder fragment(String fragment) {
        return null;
    }

    @Override
    public UriBuilder resolveTemplate(String name, Object value) {
        return null;
    }

    @Override
    public UriBuilder resolveTemplate(String name, Object value, boolean encodeSlashInPath) {
        return null;
    }

    @Override
    public UriBuilder resolveTemplateFromEncoded(String name, Object value) {
        return null;
    }

    @Override
    public UriBuilder resolveTemplates(Map<String, Object> templateValues) {
        return null;
    }

    @Override
    public UriBuilder resolveTemplates(Map<String, Object> templateValues, boolean encodeSlashInPath) throws IllegalArgumentException {
        return null;
    }

    @Override
    public UriBuilder resolveTemplatesFromEncoded(Map<String, Object> templateValues) {
        return null;
    }

    @Override
    public URI buildFromMap(Map<String, ?> values) {
        return null;
    }

    @Override
    public URI buildFromMap(Map<String, ?> values, boolean encodeSlashInPath) throws IllegalArgumentException, UriBuilderException {
        return null;
    }

    @Override
    public URI buildFromEncodedMap(Map<String, ?> values) throws IllegalArgumentException, UriBuilderException {
        return null;
    }

    @Override
    public URI build(Object... values) throws IllegalArgumentException, UriBuilderException {
        return this.uri;
    }

    @Override
    public URI build(Object[] values, boolean encodeSlashInPath) throws IllegalArgumentException, UriBuilderException {
        return null;
    }

    @Override
    public URI buildFromEncoded(Object... values) throws IllegalArgumentException, UriBuilderException {
        return null;
    }

    @Override
    public String toTemplate() {
        return null;
    }
}