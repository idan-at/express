package com.express.core;

import com.express.http.HttpMethod;
import com.sun.net.httpserver.Headers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Request {
    private final HttpMethod method;
    private final Headers headers;
    private Map<String, String> params = new HashMap<>();


    Request(HttpMethod method, Headers headers) {
        this.method = method;
        this.headers = headers;
    }

    void setParams(Map<String, String> params) {
        this.params = params;
    }

    public List<String> getHeader(String name) {
        return headers.get(name);
    }

    /**
     * @return The request HTTP Method
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     * @return An Optional<String>. If the param exists, the option will present. Otherwise, it will be empty.
     */
    public Optional<String> getParam(String name) {
        if (params.containsKey(name)) {
            return Optional.of(params.get(name));
        } else {
            return Optional.empty();
        }
    }
}
