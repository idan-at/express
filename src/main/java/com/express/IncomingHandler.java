package com.express;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class IncomingHandler {
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, "Hello, World!".getBytes(StandardCharsets.UTF_8).length);

        OutputStream os = exchange.getResponseBody();
        os.write("Hello, World!".getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
