/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.controller;

import com.athena.web.mvc.controller.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.util.Base64;

/**
 * @author shenlong
 */
public class OutMessageController implements RestController {

    @Path("/out/message")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "this is rest body";
    }

    public static void main(String[] args) {
        String authorization = "tomcat" + ":" + "tomcat";
        byte[] rel = Base64.getEncoder().encode(authorization.getBytes());
        System.out.println(new String(rel));
    }
}