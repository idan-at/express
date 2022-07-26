package com.express.core;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

class PatternURIMatcher {
    private final String pattern;

    PatternURIMatcher(String pattern) {
        this.pattern = pattern;
    }

    HandlerMatchResult matches(URI uri) {
        if (pattern.equals("*")) {
            return new HandlerMatchResult(true);
        }

        final String[] segments = pattern.split("/");
        final String[] uriSegments = uri.toString().split("/");
        final Map<String, String> params = new HashMap<>();

        int i = 0;
        int j = 0;

        while (i < segments.length && j < uriSegments.length) {
            if (isParam(segments[i])) {
              final String name = segments[i].substring(1);
              final String value = uriSegments[j];

              params.put(name, value);
                i += 1;
                j += 1;

            } else if (patternMatches(segments[i], uriSegments[j])) {
                i += 1;
                j += 1;
            } else {
                return new HandlerMatchResult(false);
            }
        }

        return new HandlerMatchResult(
                i == segments.length && j == uriSegments.length,
                params
        );
    }

    private boolean patternMatches(String pattern, String segment) {
        return pattern.equals("*") || pattern.equals(segment);
    }

    private boolean isParam(String pattern) {
        return pattern.startsWith(":");
    }
}
