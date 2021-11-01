/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.controller;

import com.athena.web.mvc.controller.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

/**
 * @author shenlong
 */
public class OutMessageController implements RestController {

    @Path("/out/message")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "this is rest body";
    }
}