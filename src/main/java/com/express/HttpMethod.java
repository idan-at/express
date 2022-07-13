package com.express;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT");

    HttpMethod(String s) {

    }

    public static HttpMethod fromString(String method) {
        switch (method) {
            case "GET":
                return GET;
            case "POST":
                return POST;
            default:
                return PUT;
        }
    }
}
