package com.express;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Response {
    private final HttpExchange exchange;

    Response(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void send(String body) {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);

        try {
            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
