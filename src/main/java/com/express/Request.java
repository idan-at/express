package com.express;

public class Request {
    private final HttpMethod method;

    Request(HttpMethod method) {
        this.method = method;
    }

    /**
     * @return The request HTTP Method
     */
    public HttpMethod getMethod() {
        return method;
    }
}
