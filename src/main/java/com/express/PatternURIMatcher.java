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

        System.out.println(String.join(",", segments));
        System.out.println(String.join(",", uriSegments));

        return pattern.equals(uri.toString());
    }
}
