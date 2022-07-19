package com.express;

class ErrorHandlerContainer {
    private final ErrorHandler handler;
    private final HandlerContext context;

    ErrorHandlerContainer(HttpMethod method, String pattern, ErrorHandler handler) {
        context = new HandlerContext(method, pattern);
        this.handler = handler;
    }

    ErrorHandler getHandler() {
        return handler;
    }

    public HandlerContext getContext() {
        return context;
    }
}
