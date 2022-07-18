package com.express;

import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatternURIMatcherTest {
    @Test
    void equals() {
        PatternURIMatcher matcher = new PatternURIMatcher("/");

        assertTrue(matcher.matches(URI.create("/")));
    }

    @Test
    void doesNotEqual() {
        PatternURIMatcher matcher = new PatternURIMatcher("/hello");

        assertFalse(matcher.matches(URI.create("/bye")));
    }

    @Test
    void equalsMultipleSegments() {
        PatternURIMatcher matcher = new PatternURIMatcher("/a/b/c/d");

        assertTrue(matcher.matches(URI.create("/a/b/c/d")));
    }

    @Test
    void ignoresTrailingSlash() {
        PatternURIMatcher matcher = new PatternURIMatcher("/a/b/c/d");

        assertTrue(matcher.matches(URI.create("/a/b/c/d/")));
    }

    @Test
    void basicWildcard() {
        PatternURIMatcher matcher = new PatternURIMatcher("/a/b/*/d");

        assertTrue(matcher.matches(URI.create("/a/b/c/d")));
    }

    @Test
    void wildcardMismatch() {
        PatternURIMatcher matcher = new PatternURIMatcher("/a/b/*");

        assertFalse(matcher.matches(URI.create("/a/b/c/d")));
    }

    @Test
    void matchingParam() {
        PatternURIMatcher matcher = new PatternURIMatcher("/users/:id");

        assertTrue(matcher.matches(URI.create("/users/1")));
    }
}
