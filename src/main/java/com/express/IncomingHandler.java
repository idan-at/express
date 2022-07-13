package com.express;

import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.util.ArrayList;

public class IncomingHandler {
    private final ArrayList<HandlerContainer> handlerContainers = new ArrayList<>();

    public void add(HandlerContainer handlerContainer) {
        handlerContainers.add(handlerContainer);
    }

    public void handle(HttpExchange exchange) {
        final Request request = new Request();
        final Response response = new Response(exchange);

        final Handler handler = findHandler(exchange);

        handler.handle(request, response, () -> {});
    }

    private Handler findHandler(HttpExchange exchange) {
        final String method = exchange.getRequestMethod();
        final URI url = exchange.getRequestURI();

        return handlerContainers.stream().filter(handlerContainer ->
                handlerContainer.getMethod().equals(method) && handlerContainer.getPattern().equals(url.toString())
        ).findFirst().get().getHandler();
    }
}
