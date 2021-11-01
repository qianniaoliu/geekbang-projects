package com.athena.web.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yusheng
 */
public interface RestController extends Controller {

    String execute(HttpServletRequest request, HttpServletResponse response);
}
