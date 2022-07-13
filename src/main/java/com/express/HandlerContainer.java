package com.express;

import java.net.URI;

class HandlerContainer {
    private final HttpMethod method;
    private final PatternURIMatcher patternURIMatcher;
    private final Handler handler;

    HandlerContainer(HttpMethod method, String pattern, Handler handler) {
        this.method = method;
        this.patternURIMatcher = new PatternURIMatcher(pattern);
        this.handler = handler;
    }

    boolean matches(String method, URI uri) {
        return this.method.toString().equals(method) && patternURIMatcher.matches(uri);
    }

    Handler getHandler() {
        return handler;
    }
}
