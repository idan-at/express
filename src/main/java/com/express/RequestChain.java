package com.express;

import java.util.Iterator;

class RequestChain {
    private final Iterator<Handler> handlersIterator;

    RequestChain(Iterator<Handler> handlersIterator) {
        this.handlersIterator = handlersIterator;
    }

    void run(Request request, Response response) {
        if (handlersIterator.hasNext()) {
            Handler handler = handlersIterator.next();

            handler.handle(request, response, new NextFunctionHandler() {
                @Override
                public void ok() {
                    run(request, response);
                }

                @Override
                public void error(Exception e) {

                }
            });
        }
    }
}
