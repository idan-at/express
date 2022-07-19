package com.express.http;

import com.express.http.HttpMethod;
import com.express.http.UnsupportedHttpMethodException;
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
    void fromString_PATCH() {
        assertEquals(HttpMethod.PATCH, HttpMethod.fromString("PATCH"));
    }

    @Test
    void fromString_DELETE() {
        assertEquals(HttpMethod.DELETE, HttpMethod.fromString("DELETE"));
    }

    @Test
    void fromString_unrecognized() {
        assertThrows(UnsupportedHttpMethodException.class, () -> HttpMethod.fromString("NOT-A-METHOD"));
    }
}