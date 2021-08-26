package com.athena.web.mvc;

import com.athena.web.mvc.controller.Controller;
import com.athena.web.mvc.controller.PageController;
import com.athena.web.mvc.controller.RestController;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author yusheng
 */
public class FrontControllerServlet extends HttpServlet {

    private Map<String, HandleMethod> handleMethodMapping = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("FrontControllerServlet init success");
    }

    private void initHandleMethods() {
        ServiceLoader<Controller> serviceLoaders = ServiceLoader.load(Controller.class);
        serviceLoaders.stream().forEach(controllerProvider -> {
            Class<?> controllerClass = controllerProvider.get().getClass();
            Path controllerPath = controllerClass.getAnnotation(Path.class);
            String controllerPathUrl = prettyPathUrl(controllerPath);
            Method[] methods = controllerClass.getMethods();
            if(methods != null && methods.length > 0){
                Stream.of(methods).forEach(method -> {
                    Path methodPath = method.getAnnotation(Path.class);
                    if(methodPath != null){
                        String methodPathUrl = prettyPathUrl(methodPath);
                        String fullPathUrl = controllerPathUrl + methodPathUrl;
                        HandleMethod handleMethod = new HandleMethod(fullPathUrl, method, controllerProvider.get());
                        Set<String> supportMethodTypes = getSupportMethodType(method);
                        handleMethod.setSupportMethodTypes(supportMethodTypes);

                        handleMethodMapping.put(fullPathUrl, handleMethod);
                    }
                });
            }
        });
    }

    private Set<String> getSupportMethodType(Method method) {
        Set<String> result = new HashSet<>();
        Stream.of(method.getAnnotations()).forEach(annotation -> {
            HttpMethod httpMethod = annotation.annotationType().getAnnotation(HttpMethod.class);
            if(Objects.nonNull(httpMethod)){
                result.add(httpMethod.value());
            }
        });
        if(result.size() == 0){
            result.addAll(Arrays.asList(HttpMethod.GET,HttpMethod.DELETE,HttpMethod.HEAD,
                    HttpMethod.OPTIONS,HttpMethod.PATCH,HttpMethod.POST,HttpMethod.PUT));
        }
        return result;
    }


    private String prettyPathUrl(Path path){
        String pathUrl = "";
        if(path != null && StringUtils.isNotBlank(path.value())){
            pathUrl = path.value();
        }
        if(StringUtils.isNotBlank(pathUrl) && !pathUrl.startsWith("/")){
            pathUrl = "/" + pathUrl;
        }
        return pathUrl;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        System.out.println(requestUri);
        HandleMethod handleMethod = handleMethodMapping.get(requestUri);
        if(Objects.isNull(handleMethod)){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String httpMethod = request.getMethod();
        if(!handleMethod.getSupportMethodTypes().contains(httpMethod)){
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        try{
            Object controller = handleMethod.getController();
            if(controller instanceof PageController) {
                PageController pageController = (PageController) controller;
                String viewPath = pageController.execute(request, response);
                ServletContext servletContext = request.getServletContext();
                servletContext.getRequestDispatcher(viewPath).forward(request, response);
            }else if(controller instanceof RestController){
                Object result = handleMethod.getMethod().invoke(handleMethod.getController(), null);
            }
        }catch (Exception ex){

        }


        System.out.println("in FrontControllerServlet");
    }
}
