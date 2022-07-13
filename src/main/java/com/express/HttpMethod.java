package com.express;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    HttpMethod(String s) {

    }

    public static HttpMethod fromString(String method) {
        switch (method) {
            case "GET":
                return GET;
            case "POST":
                return POST;
            case "PUT":
                return PUT;
            default:
                return DELETE;
        }
    }
}
