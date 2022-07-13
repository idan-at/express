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

        final Iterator<Handler> handlers = buildHandlersChain(exchange);

        final Handler first = handlers.next();
        final NextFunctionHandler next = new NextFunctionHandlerImpl(request, response, handlers);

        first.handle(request, response, next);
    }

    private Iterator<Handler> buildHandlersChain(HttpExchange exchange) {
        final String method = exchange.getRequestMethod();
        final URI url = exchange.getRequestURI();

        return handlerContainers.stream()
                .filter(handlerContainer -> handlerContainer.matches(method, url))
                .map(HandlerContainer::getHandler)
                .iterator();
    }
}
