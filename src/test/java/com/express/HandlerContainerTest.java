package com.express;

import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class HandlerContainerTest {
    static Handler handler = (req, res, next) -> {};

    @Test
    void matches_sanity() {
        HandlerContainer handlerContainer = new HandlerContainer(HttpMethod.GET, "/", handler);

        assertTrue(handlerContainer.matches(HttpMethod.GET.toString(), URI.create("/")));;
    }

    @Test
    void matches_httpMethodMismatch() {
        HandlerContainer handlerContainer = new HandlerContainer(HttpMethod.GET, "/", handler);

        assertFalse(handlerContainer.matches(HttpMethod.POST.toString(), URI.create("/")));;
    }

    @Test
    void matches_patternMismatch() {
        HandlerContainer handlerContainer = new HandlerContainer(HttpMethod.GET, "/", handler);

        assertFalse(handlerContainer.matches(HttpMethod.GET.toString(), URI.create("/abc")));;
    }
}