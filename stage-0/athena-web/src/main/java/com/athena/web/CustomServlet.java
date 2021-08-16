/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shenlong
 * @version CustomServlet.java, v 0.1 2021年08月15日 8:50 下午 shenlong
 */
public class CustomServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("CustomServlet init success");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CustomServlet doService");
    }
}