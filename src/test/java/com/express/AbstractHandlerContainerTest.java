package com.express;

import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class AbstractHandlerContainerTest {
    static class SomeHandlerContainer extends AbstractHandlerContainer {
        SomeHandlerContainer(HttpMethod method, String pattern) {
            super(method, pattern);
        }
    }

    @Test
    void matches() {
        SomeHandlerContainer handlerContainer = new SomeHandlerContainer(HttpMethod.GET, "/");

        assertTrue(handlerContainer.matches(HttpMethod.GET.toString(), URI.create("/")));;
    }

    @Test
    void httpMethodMismatch() {
        SomeHandlerContainer handlerContainer = new SomeHandlerContainer(HttpMethod.GET, "/");

        assertFalse(handlerContainer.matches(HttpMethod.POST.toString(), URI.create("/")));;
    }

    @Test
    void patternMismatch() {
        SomeHandlerContainer handlerContainer = new SomeHandlerContainer(HttpMethod.GET, "/hello");

        assertFalse(handlerContainer.matches(HttpMethod.GET.toString(), URI.create("/wrong")));;
    }
}
