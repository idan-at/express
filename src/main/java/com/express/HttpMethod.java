package com.express;

public enum HttpMethod {
    GET("GET"),
    POST("POST");

    HttpMethod(String s) {

    }

    public static HttpMethod fromString(String method) {
        switch (method) {
            case "GET":
                return GET;
            default:
                return POST;
        }
    }
}
