package com.express;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpMethodTest {
    @Test
    void fromString_GET() { assertEquals(HttpMethod.GET, HttpMethod.fromString("GET")); }

    @Test
    void fromString_POST() {
        assertEquals(HttpMethod.POST, HttpMethod.fromString("POST"));
    }

    @Test
    void fromString_PUT() {
        assertEquals(HttpMethod.PUT, HttpMethod.fromString("PUT"));
    }

    @Test
    void fromString_DELETE() {
        assertEquals(HttpMethod.DELETE, HttpMethod.fromString("DELETE"));
    }
}