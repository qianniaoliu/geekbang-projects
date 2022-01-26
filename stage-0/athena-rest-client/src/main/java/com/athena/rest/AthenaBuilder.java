/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.rest;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.CompletionStageRxInvoker;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.RxInvoker;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.MalformedURLException;
import java.util.Locale;

/**
 * @author shenlong
 * @version AthenaBuilder.java, v 0.1 2021��12��06�� 8:32 ���� shenlong
 */
public class AthenaBuilder implements Builder {

    private final UriBuilder uriBuilder;

    private MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();

    public AthenaBuilder(UriBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    @Override
    public Invocation build(String method) {
        return null;
    }

    @Override
    public Invocation build(String method, Entity<?> entity) {
        return null;
    }

    @Override
    public Invocation buildGet() {
        try {
            return new AthenaHttpGetInvocation(uriBuilder.build().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Invocation buildDelete() {
        return null;
    }

    @Override
    public Invocation buildPost(Entity<?> entity) {
        return null;
    }

    @Override
    public Invocation buildPut(Entity<?> entity) {
        return null;
    }

    @Override
    public AsyncInvoker async() {
        return null;
    }

    @Override
    public Builder accept(String... mediaTypes) {
        return null;
    }

    @Override
    public Builder accept(MediaType... mediaTypes) {
        return null;
    }

    @Override
    public Builder acceptLanguage(Locale... locales) {
        return null;
    }

    @Override
    public Builder acceptLanguage(String... locales) {
        return null;
    }

    @Override
    public Builder acceptEncoding(String... encodings) {
        return null;
    }

    @Override
    public Builder cookie(Cookie cookie) {
        return null;
    }

    @Override
    public Builder cookie(String name, String value) {
        return null;
    }

    @Override
    public Builder cacheControl(CacheControl cacheControl) {
        return null;
    }

    @Override
    public Builder header(String name, Object value) {
        headers.add(name, value);
        return this;
    }

    @Override
    public Builder headers(MultivaluedMap<String, Object> headers) {
        return null;
    }

    @Override
    public Builder property(String name, Object value) {
        return null;
    }

    @Override
    public CompletionStageRxInvoker rx() {
        return null;
    }

    @Override
    public <T extends RxInvoker> T rx(Class<T> clazz) {
        return null;
    }

    @Override
    public Response get() {
        return null;
    }

    @Override
    public <T> T get(Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T get(GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response put(Entity<?> entity) {
        return null;
    }

    @Override
    public <T> T put(Entity<?> entity, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T put(Entity<?> entity, GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response post(Entity<?> entity) {
        return null;
    }

    @Override
    public <T> T post(Entity<?> entity, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T post(Entity<?> entity, GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response delete() {
        return null;
    }

    @Override
    public <T> T delete(Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T delete(GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response head() {
        return null;
    }

    @Override
    public Response options() {
        return null;
    }

    @Override
    public <T> T options(Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T options(GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response trace() {
        return null;
    }

    @Override
    public <T> T trace(Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T trace(GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response method(String name) {
        return null;
    }

    @Override
    public <T> T method(String name, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T method(String name, GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response method(String name, Entity<?> entity) {
        return null;
    }

    @Override
    public <T> T method(String name, Entity<?> entity, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T method(String name, Entity<?> entity, GenericType<T> responseType) {
        return null;
    }
}