package com.express.core.handler;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternURIMatcherTest {
    @Test
    void catchAll() {
        PatternURIMatcher matcher = new PatternURIMatcher("*");

        assertEquals(matcher.matches(URI.create("/")), new HandlerMatchResult(true));
        assertEquals(matcher.matches(URI.create("/some-url")), new HandlerMatchResult(true));
        assertEquals(matcher.matches(URI.create("/some/deep/url")), new HandlerMatchResult(true));
    }

    @Test
    void equals() {
        PatternURIMatcher matcher = new PatternURIMatcher("/");

        assertEquals(matcher.matches(URI.create("/")), new HandlerMatchResult(true));
    }

    @Test
    void doesNotEqual() {
        PatternURIMatcher matcher = new PatternURIMatcher("/hello");

        assertEquals(matcher.matches(URI.create("/bye")), new HandlerMatchResult(false));
    }

    @Test
    void equalsMultipleSegments() {
        PatternURIMatcher matcher = new PatternURIMatcher("/a/b/c/d");

        assertEquals(matcher.matches(URI.create("/a/b/c/d")), new HandlerMatchResult(true));
    }

    @Test
    void ignoresTrailingSlash() {
        PatternURIMatcher matcher = new PatternURIMatcher("/a/b/c/d");

        assertEquals(matcher.matches(URI.create("/a/b/c/d/")), new HandlerMatchResult(true));
    }

    @Test
    void basicWildcard() {
        PatternURIMatcher matcher = new PatternURIMatcher("/a/b/*/d");

        assertEquals(matcher.matches(URI.create("/a/b/c/d")), new HandlerMatchResult(true));
    }

    @Test
    void wildcardMismatch() {
        PatternURIMatcher matcher = new PatternURIMatcher("/a/b/*");

        assertEquals(matcher.matches(URI.create("/a/b/c/d")), new HandlerMatchResult(false));
    }

    @Test
    void matchingParam() {
        PatternURIMatcher matcher = new PatternURIMatcher("/users/:id");

        assertEquals(matcher.matches(URI.create("/users/1")), new HandlerMatchResult(true, new HashMap<>() {{
          put("id", "1");
        }}));
    }
}
