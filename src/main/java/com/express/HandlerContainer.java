package com.express;

class HandlerContainer {
    private final HttpMethod method;
    private final String pattern;
    private final Handler handler;

    public HandlerContainer(HttpMethod method, String pattern, Handler handler) {
        this.method = method;
        this.pattern = pattern;
        this.handler = handler;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPattern() {
        return pattern;
    }

    public Handler getHandler() {
        return handler;
    }
}
