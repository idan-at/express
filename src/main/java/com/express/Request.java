package com.express;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Request {
    private final HttpMethod method;

    Request(HttpMethod method) {
        this.method = method;
    }

    /**
     * @return The request HTTP Method
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     * @return An Optional<String>. If the param exists, the option will present. Otherwise, it will be empty.
     */
    public Optional<String> getParam(String name) {
        return Optional.empty();
    }
}
