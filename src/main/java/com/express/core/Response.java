package com.express.core;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class Response {
    private final HttpExchange exchange;
    private final Logger logger;
    private int status = 200;

    Response(HttpExchange exchange) {
        this.exchange = exchange;
        logger = Logger.getLogger(Response.class.getName());
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

        send(status, bytes);
    }

    /**
     * Finalizes the request by sending the given HTTP status
     *
     * @param status The HTTP status to send
     */
    public void sendStatus(int status) {
        send(status, new byte[] {});
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

    private void send(int status, byte[] body) {
        try {
            exchange.sendResponseHeaders(status, body.length);

            if (body.length > 0) {
                OutputStream os = exchange.getResponseBody();
                os.write(body);
                os.close();
            }

            exchange.close();
        } catch (IOException e) {
            logger.warning("send failed: " + e.getMessage());

            throw new RuntimeException(e);
        }
    }
}
