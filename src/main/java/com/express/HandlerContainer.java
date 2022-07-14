package com.express;

class HandlerContainer extends AbstractHandlerContainer {
    private final Handler handler;

    HandlerContainer(HttpMethod method, String pattern, Handler handler) {
        super(method, pattern);
        this.handler = handler;
    }

    Handler getHandler() {
        return handler;
    }
}
