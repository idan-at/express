package com.express.core;

import com.express.http.HttpMethod;
import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;

public class RequestHandler {
    private final ArrayList<HandlerContainer> handlerContainers = new ArrayList<>();
    private final ArrayList<ErrorHandlerContainer> errorHandlerContainers = new ArrayList<>();

    public void add(HandlerContainer handlerContainer) {
        handlerContainers.add(handlerContainer);
    }

    public void add(ErrorHandlerContainer errorHandlerContainer) {
        errorHandlerContainers.add(errorHandlerContainer);
    }

    public void handle(HttpExchange exchange) {
        HttpMethod method = HttpMethod.fromString(exchange.getRequestMethod());

        final Request request = new Request(method);
        final Response response = new Response(exchange);

        final RequestChain requestChain = new RequestChainBuilder()
                .withHandlers(handlerContainers)
                .withErrorHandlers(errorHandlerContainers)
                .build(exchange);

        requestChain.run(request, response);
    }
}
