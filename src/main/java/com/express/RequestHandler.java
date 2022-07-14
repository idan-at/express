package com.express;

import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

class RequestHandler {
    private final ArrayList<HandlerContainer> handlerContainers = new ArrayList<>();

    void add(HandlerContainer handlerContainer) {
        handlerContainers.add(handlerContainer);
    }

    void handle(HttpExchange exchange) {
        HttpMethod method = HttpMethod.fromString(exchange.getRequestMethod());

        final Request request = new Request(method);
        final Response response = new Response(exchange);

        final RequestChain requestChain = new RequestChainBuilder().withHandlers(handlerContainers).build(exchange);

        requestChain.run(request, response);
    }
}
