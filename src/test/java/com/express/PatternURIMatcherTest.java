package com.express;

import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class PatternURIMatcherTest {
    @Test
    void equals() {
        PatternURIMatcher matcher = new PatternURIMatcher("/");

        assertTrue(matcher.matches(URI.create("/")));
    }

    @Test
    void equalsMultipleSegments() {
        PatternURIMatcher matcher = new PatternURIMatcher("/a/b/c/d");

        assertTrue(matcher.matches(URI.create("/a/b/c/d")));
    }
}