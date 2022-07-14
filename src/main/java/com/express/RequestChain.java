package com.express;

import java.util.Iterator;

class RequestChain {
    private final Iterator<Handler> handlersIterator;
    private final Iterator<ErrorHandler> errorHandlersIterator;

    RequestChain(Iterator<Handler> handlersIterator, Iterator<ErrorHandler> errorHandlersIterator) {
        this.handlersIterator = handlersIterator;
        this.errorHandlersIterator = errorHandlersIterator;
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
                    runError(e, request, response);
                }
            });
        }
    }

    private void runError(Exception e, Request request, Response response) {
        if (errorHandlersIterator.hasNext()) {
            ErrorHandler errorHandler = errorHandlersIterator.next();

            errorHandler.handle(e, request, response, new NextFunctionHandler() {
                @Override
                public void ok() {
                    throw new RuntimeException("next.ok() can't be invoked from a ErrorHandler");
                }

                @Override
                public void error(Exception e) {
                    runError(e, request, response);
                }
            });
        }
    }
}
