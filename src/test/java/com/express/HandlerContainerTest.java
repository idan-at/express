package com.express;

import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

// TODO: test against the abstract class
class HandlerContainerTest {
    static Handler handler = (req, res, next) -> {};

    @Test
    void matches() {
        HandlerContainer handlerContainer = new HandlerContainer(HttpMethod.GET, "/", handler);

        assertTrue(handlerContainer.matches(HttpMethod.GET.toString(), URI.create("/")));;
    }

    @Test
    void httpMethodMismatch() {
        HandlerContainer handlerContainer = new HandlerContainer(HttpMethod.GET, "/", handler);

        assertFalse(handlerContainer.matches(HttpMethod.POST.toString(), URI.create("/")));;
    }

    @Test
    void patternMismatch() {
        HandlerContainer handlerContainer = new HandlerContainer(HttpMethod.GET, "/hello", handler);

        assertFalse(handlerContainer.matches(HttpMethod.GET.toString(), URI.create("/wrong")));;
    }
}
