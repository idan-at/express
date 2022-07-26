package com.express.core;

import com.express.api.ErrorHandler;
import com.express.api.Handler;
import com.express.core.*;
import com.express.core.handler.ErrorHandlerContainer;
import com.express.core.handler.HandlerContainer;
import com.express.http.HttpMethod;
import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;

public class RequestHandler {
    private final ArrayList<HandlerContainer> handlerContainers = new ArrayList<>();
    private final ArrayList<ErrorHandlerContainer> errorHandlerContainers = new ArrayList<>();

    public void add(HttpMethod method, String pattern, Handler handler) {
        handlerContainers.add(new HandlerContainer(method, pattern, handler));
    }

    public void add(HttpMethod method, String pattern, ErrorHandler handler) {
        errorHandlerContainers.add(new ErrorHandlerContainer(method, pattern, handler));
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
