package com.express;

class HandlerContainer {
    private final Handler handler;
    private final HandlerContext context;

    HandlerContainer(HttpMethod method, String pattern, Handler handler) {
        context = new HandlerContext(method, pattern);
        this.handler = handler;
    }

    Handler getHandler() {
        return handler;
    }

    public HandlerContext getContext() {
        return context;
    }
}
