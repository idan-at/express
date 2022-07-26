package com.express.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HandlerMatchResult {
    private final boolean matches;
    private final Map<String, String> params;

    HandlerMatchResult(boolean matches, Map<String, String> params) {
        this.matches = matches;
        this.params = params;
    }

    HandlerMatchResult(boolean matches) {
        this(matches, new HashMap<>());
    }


    public boolean isMatch() {
        return matches;
    }

    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerMatchResult that = (HandlerMatchResult) o;
        return matches == that.matches && Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matches, params);
    }
}
