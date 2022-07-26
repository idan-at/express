package com.express.core;

import com.express.http.HttpMethod;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HandlerContextTest {
    private final Random random = new Random();

    HttpMethod randomHttpMethod() {
        final HttpMethod[] values = HttpMethod.values();

        final int index = random.nextInt(values.length);

        return values[index];
    }

    @Test
    void matches() {
        HandlerContext context = new HandlerContext(HttpMethod.GET, "/");

        assertEquals(context.matches(HttpMethod.GET.toString(), URI.create("/")), new HandlerMatchResult(true));
    }

    @Test
    void matchesAnyHttpMethod() {
        HandlerContext context = new HandlerContext(null, "/");

        assertEquals(context.matches(randomHttpMethod().toString(), URI.create("/")), new HandlerMatchResult(true));
    }


    @Test
    void httpMethodMismatch() {
        HandlerContext context = new HandlerContext(HttpMethod.GET, "/");

        assertEquals(context.matches(HttpMethod.POST.toString(), URI.create("/")), new HandlerMatchResult(false));
    }

    @Test
    void patternMismatch() {
        HandlerContext context = new HandlerContext(HttpMethod.GET, "/hello");

        assertEquals(context.matches(HttpMethod.GET.toString(), URI.create("/wrong")), new HandlerMatchResult(false));
    }
}
