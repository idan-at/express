package com.express;

// TODO: Add IT and test all features
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
