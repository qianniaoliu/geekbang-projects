package com.athena.web.mvc;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author yusheng
 */
public class HandleMethod {

    private String requestUrl;

    private Method method;

    private Object controller;

    private Set<String> supportMethodTypes;

    public HandleMethod(String requestUrl, Method method, Object controller) {
        this.requestUrl = requestUrl;
        this.method = method;
        this.controller = controller;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Set<String> getSupportMethodTypes() {
        return supportMethodTypes;
    }

    public void setSupportMethodTypes(Set<String> supportMethodTypes) {
        this.supportMethodTypes = supportMethodTypes;
    }
}
