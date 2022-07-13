package com.express;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpMethodTest {
    @Test
    void fromString_GET() {
        assertEquals(HttpMethod.GET, HttpMethod.fromString("GET"));
    }

    @Test
    void fromString_POST() {
        assertEquals(HttpMethod.POST, HttpMethod.fromString("POST"));
    }
}