package com.express;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    @Test
    void close_doesNotThrowIfNotStarted() {
        Application application = new Application();

        assertDoesNotThrow(() -> application.close());
    }
}
