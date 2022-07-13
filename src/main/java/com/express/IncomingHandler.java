package com.express;

import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class IncomingHandler {
    private final ArrayList<HandlerContainer> handlerContainers = new ArrayList<>();

    public void add(HandlerContainer handlerContainer) {
        handlerContainers.add(handlerContainer);
    }

    public void handle(HttpExchange exchange) {
        final Request request = new Request();
        final Response response = new Response(exchange);

        final List<Handler> handlers = buildHandlersChain(exchange);
        final NextFunctionHandler next = new NextFunctionHandlerImpl();

        handlers.forEach(handler -> handler.handle(request, response, next));
    }

    private List<Handler> buildHandlersChain(HttpExchange exchange) {
        final String method = exchange.getRequestMethod();
        final URI url = exchange.getRequestURI();

        return handlerContainers.stream().filter(handlerContainer ->
            handlerContainer.getMethod().toString().equals(method) && handlerContainer.getPattern().equals(url.toString())
        ).map(HandlerContainer::getHandler).collect(Collectors.toList());
    }
}
