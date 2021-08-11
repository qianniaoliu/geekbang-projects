package com.athena.web.mvc;

import com.athena.web.mvc.controller.Controller;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ServiceLoader;
import java.util.stream.Stream;

/**
 * @author yusheng
 */
public class FrontControllerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        initHandleMethods();
    }

    private void initHandleMethods() {
        ServiceLoader<Controller> serviceLoaders = ServiceLoader.load(Controller.class);
        serviceLoaders.stream().forEach(controllerProvider -> {
            Class<?> controllerClass = controllerProvider.get().getClass();
            Path controllerPath = controllerClass.getAnnotation(Path.class);
            Method[] methods = controllerClass.getMethods();
            if(methods != null && methods.length > 0){
                Stream.of(methods).forEach(method -> {
                    Path methodPath = method.getAnnotation(Path.class);
                    if(methodPath != null){
                        String controllerPathUrl = "";
                        if(controllerPath != null && StringUtils.isNotBlank(controllerPath.value())){
                            controllerPathUrl = controllerPath.value();
                        }
                        if(StringUtils.isNotBlank(controllerPathUrl) && !controllerPathUrl.startsWith("/")){
                            controllerPathUrl = "/" + controllerPathUrl;
                        }

                    }
                });
            }
        });
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
