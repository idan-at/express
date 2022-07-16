package com.express;

import java.net.URI;

class PatternURIMatcher {
    private final String pattern;

    PatternURIMatcher(String pattern) {
        this.pattern = pattern;
    }

    boolean matches(URI uri) {
        final String[] segments = pattern.split("/");
        final String[] uriSegments = uri.toString().split("/");

        int i = 0;
        int j = 0;

        while (i < segments.length && j < uriSegments.length) {
            if (segments[i].equals("*") || segments[i].equals(uriSegments[j])) {
                i += 1;
                j += 1;
            } else {
                return false;
            }
        }

        return true;
    }
}
