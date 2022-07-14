package com.express;

class ErrorHandlerContainer extends AbstractHandlerContainer {
    private final ErrorHandler handler;

    ErrorHandlerContainer(HttpMethod method, String pattern, ErrorHandler handler) {
        super(method, pattern);
        this.handler = handler;
    }

    ErrorHandler getHandler() {
        return handler;
    }
}
