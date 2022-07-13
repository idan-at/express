package com.express;

import com.express.handlerfunctions.Handler;

public class HandlerContainer {
    private final String method;
    private final String pattern;
    private final Handler handler;


    public HandlerContainer(String method, String pattern, Handler handler) {
        this.method = method;
        this.pattern = pattern;
        this.handler = handler;
    }

    public String getMethod() {
        return method;
    }

    public String getPattern() {
        return pattern;
    }

    public Handler getHandler() {
        return handler;
    }
}
