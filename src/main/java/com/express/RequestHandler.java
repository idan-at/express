package com.express;

import com.express.http.HttpMethod;
import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;

class RequestHandler {
    private final ArrayList<HandlerContainer> handlerContainers = new ArrayList<>();
    private final ArrayList<ErrorHandlerContainer> errorHandlerContainers = new ArrayList<>();

    void add(HandlerContainer handlerContainer) {
        handlerContainers.add(handlerContainer);
    }

    void add(ErrorHandlerContainer errorHandlerContainer) {
        errorHandlerContainers.add(errorHandlerContainer);
    }

    void handle(HttpExchange exchange) {
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
