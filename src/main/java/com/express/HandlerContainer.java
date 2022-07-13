package com.express;

import java.net.URI;

class HandlerContainer {
    private final HttpMethod method;
    private final String pattern;
    private final Handler handler;

    HandlerContainer(HttpMethod method, String pattern, Handler handler) {
        this.method = method;
        this.pattern = pattern;
        this.handler = handler;
    }

    boolean matches(String method, URI uri) {
        return this.method.toString().equals(method) && pattern.equals(uri.toString());
    }

    Handler getHandler() {
        return handler;
    }
}
