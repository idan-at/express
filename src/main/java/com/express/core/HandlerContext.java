package com.express.core;

import com.express.http.HttpMethod;

import java.net.URI;

class HandlerContext {
    private final HttpMethod method;
    private final PatternURIMatcher patternURIMatcher;

    HandlerContext(HttpMethod method, String pattern) {
        this.method = method;
        this.patternURIMatcher = new PatternURIMatcher(pattern);
    }

    HandlerMatchResult matches(String method, URI uri) {
        if (!methodMatches(method)) {
            return new HandlerMatchResult(false);
        }

        return patternURIMatcher.matches(uri);
    }

    private boolean methodMatches(String method) {
        return this.method == null || this.method.toString().equals(method);
    }
}
