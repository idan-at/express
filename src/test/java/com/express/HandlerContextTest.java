package com.express;

import com.express.http.HttpMethod;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandlerContextTest {
    @Test
    void matches() {
        HandlerContext context = new HandlerContext(HttpMethod.GET, "/");

        assertTrue(context.matches(HttpMethod.GET.toString(), URI.create("/")));;
    }

    @Test
    void httpMethodMismatch() {
        HandlerContext context = new HandlerContext(HttpMethod.GET, "/");

        assertFalse(context.matches(HttpMethod.POST.toString(), URI.create("/")));;
    }

    @Test
    void patternMismatch() {
        HandlerContext context = new HandlerContext(HttpMethod.GET, "/hello");

        assertFalse(context.matches(HttpMethod.GET.toString(), URI.create("/wrong")));;
    }
}
