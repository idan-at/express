package com.express;

import java.net.URI;
import java.util.Arrays;

class PatternURIMatcher {
    private final String pattern;

    PatternURIMatcher(String pattern) {
        this.pattern = pattern;
    }

    boolean matches(URI uri) {
        String[] segments = pattern.split("/");
        String[] uriSegments = uri.toString().split("/");

        return pattern.equals(uri.toString());
    }
}
