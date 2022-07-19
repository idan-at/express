package com.express.http;

public class UnsupportedHttpMethodException extends RuntimeException {
    UnsupportedHttpMethodException(String method) {
        super("Unsupported HTTP Method: " + method);
    }
}
