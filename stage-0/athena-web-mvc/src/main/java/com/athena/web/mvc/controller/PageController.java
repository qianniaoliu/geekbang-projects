/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.web.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shenlong
 * @version PageController.java,
 */
public interface PageController extends Controller{

    String execute(HttpServletRequest request, HttpServletResponse response);
}