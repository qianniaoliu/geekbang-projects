/*
 * Ant Group
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.athena.web.listener;

import com.athena.context.ClassicComponentContext;
import com.athena.web.mvc.FrontControllerServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;

/**
 * @author shenlong
 */
public class ComponentContextInitializerListener implements ServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ServletRegistration.Dynamic reg = servletContext.addServlet("frontControllerServlet", FrontControllerServlet.class);
        reg.addMapping("/");
        this.servletContext = servletContext;
        ClassicComponentContext context = new ClassicComponentContext();
        context.init(servletContext);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servlet Context destroy success");
    }
}