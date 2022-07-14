package com.express;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Response {
    private final HttpExchange exchange;
    private int status = 200;

    Response(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public Response setStatus(int status) {
        this.status = status;

        return this;
    }

    public void send(String body) {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);

        try {
            exchange.sendResponseHeaders(this.status, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendStatus(int status) {
        try {
            exchange.sendResponseHeaders(status, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
