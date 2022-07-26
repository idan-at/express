package com.express.core.handler;

import com.express.api.ErrorHandler;
import com.express.http.HttpMethod;

public class ErrorHandlerContainer {
    private final ErrorHandler handler;
    private final HandlerContext context;

    public ErrorHandlerContainer(HttpMethod method, String pattern, ErrorHandler handler) {
        context = new HandlerContext(method, pattern);
        this.handler = handler;
    }

    public ErrorHandler getHandler() {
        return handler;
    }

    public HandlerContext getContext() {
        return context;
    }
}
