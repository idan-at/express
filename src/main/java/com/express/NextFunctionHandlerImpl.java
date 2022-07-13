package com.express;

import java.util.Iterator;

class NextFunctionHandlerImpl implements NextFunctionHandler {
    private final Request request;
    private final Response response;
    private final Iterator<Handler> iterator;

    NextFunctionHandlerImpl(Request request, Response response, Iterator<Handler> handlerIterator) {
            this.request = request;
            this.response = response;
            iterator = handlerIterator;
    }

    @Override
    public void ok() {
        final Handler handler = iterator.next();

        handler.handle(request, response, new NextFunctionHandlerImpl(request, response, iterator));
    }

    @Override
    public void error(Exception e) {

    }
}
