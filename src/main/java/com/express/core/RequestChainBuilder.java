package com.express.core;

import com.express.core.handler.ErrorHandlerContainer;
import com.express.core.handler.HandlerContainer;
import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class RequestChainBuilder {
    private List<HandlerContainer> handlerContainers = new ArrayList<>();
    private List<ErrorHandlerContainer> errorHandlerContainers = new ArrayList<>();

    public RequestChainBuilder withHandlers(List<HandlerContainer> handlerContainers) {
        this.handlerContainers = handlerContainers;

        return this;
    }

    public RequestChainBuilder withErrorHandlers(List<ErrorHandlerContainer> errorHandlerContainers) {
        this.errorHandlerContainers = errorHandlerContainers;

        return this;
    }

    public RequestChain build(HttpExchange exchange) {
        final String method = exchange.getRequestMethod();
        final URI url = exchange.getRequestURI();

        return new RequestChain(
            handlerContainers.stream()
                .filter(handlerContainer -> handlerContainer.getContext().matches(method, url).isMatch())
                .iterator(),
            errorHandlerContainers.stream()
                .filter(handlerContainer -> handlerContainer.getContext().matches(method, url).isMatch())
                .iterator()
        );
    }
}
