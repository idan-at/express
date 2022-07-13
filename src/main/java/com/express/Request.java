package com.express;

public class Request {
    private final HttpMethod method;

    Request(HttpMethod method) {
        this.method = method;
    }

    public HttpMethod getMethod() {
        return method;
    }
}
