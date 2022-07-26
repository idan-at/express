package com.express.core;

import com.express.api.Handler;
import com.express.core.handler.ErrorHandlerContainer;
import com.express.core.handler.HandlerContainer;
import com.express.core.handler.HandlerMatchResult;
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
                        .map(handlerContainer -> new MatchedHandler(handlerContainer.getHandler(), handlerContainer.getContext().matches(method, url)))
                        .filter(matchedHandler -> matchedHandler.getMatchResult().isMatch())
                        .iterator(),
                errorHandlerContainers.stream()
                        .map(handlerContainer -> new MatchedErrorHandler(handlerContainer.getHandler(), handlerContainer.getContext().matches(method, url)))
                        .filter(matchedHandler -> matchedHandler.getMatchResult().isMatch())
                        .iterator()
        );
    }
}
