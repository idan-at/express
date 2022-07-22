package com.express.core;

import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class RequestChainBuilder {
    private List<HandlerContainer> handlerContainers = new ArrayList<>();
    private List<ErrorHandlerContainer> errorHandlerContainers = new ArrayList<>();

    RequestChainBuilder withHandlers(List<HandlerContainer> handlerContainers) {
        this.handlerContainers = handlerContainers;

        return this;
    }

    RequestChainBuilder withErrorHandlers(List<ErrorHandlerContainer> errorHandlerContainers) {
        this.errorHandlerContainers = errorHandlerContainers;

        return this;
    }

    RequestChain build(HttpExchange exchange) {
        final String method = exchange.getRequestMethod();
        final URI url = exchange.getRequestURI();

        return new RequestChain(
            handlerContainers.stream()
                .filter(handlerContainer -> handlerContainer.getContext().matches(method, url))
                .iterator(),
            errorHandlerContainers.stream()
                .filter(handlerContainer -> handlerContainer.getContext().matches(method, url))
                .iterator()
        );
    }
}
