/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlong
 * @version RestDemo.java, v 0.1 2021年12月06日 4:00 下午 shenlong
 */
public class RestDemo {

    public static void main(String[] args) {
        Map<String, Object> templateValues = new HashMap<>();
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://127.0.0.1:8001/hello/world")
                .queryParam("message", "Hello,World")
                .resolveTemplates(templateValues)
                .request()
                .header("head", "hello")
                .buildGet()
                .invoke();
        String message = response.readEntity(String.class);
        System.out.println(message);
    }
}