package com.express;

import java.net.URI;

class HandlerContext {
    private final HttpMethod method;
    private final PatternURIMatcher patternURIMatcher;

    HandlerContext(HttpMethod method, String pattern) {
        this.method = method;
        this.patternURIMatcher = new PatternURIMatcher(pattern);
    }

    boolean matches(String method, URI uri) {
        return this.method.toString().equals(method) && patternURIMatcher.matches(uri);
    }
}
