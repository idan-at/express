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

    /**
     * Sets the HTTP status to send when the request is finalized.
     * @param status The status to set.
     * @return The Response
     */
    public Response setStatus(int status) {
        this.status = status;

        return this;
    }

    /**
     * Finalizes the response and sends the given body.
     * @param body The string body to send.
     */
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

    /**
     * Finalizes the request by sending the given HTTP status
     *
     * @param status The HTTP status to send
     */
    public void sendStatus(int status) {
        try {
            exchange.sendResponseHeaders(status, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the given header
     * @param name The header name
     * @param value The header value
     * @return The Response
     */
    public Response setHeader(String name, String value) {
        exchange.getResponseHeaders().set(name, value);

        return this;
    }
}
