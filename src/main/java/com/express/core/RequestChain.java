package com.express.core;

import com.express.api.ErrorHandler;
import com.express.api.Handler;
import com.express.api.NextFunctionHandler;

import java.util.Iterator;

class RequestChain {
    private final Iterator<HandlerContainer> handlersIterator;
    private final Iterator<ErrorHandlerContainer> errorHandlersIterator;

    RequestChain(Iterator<HandlerContainer> handlersIterator, Iterator<ErrorHandlerContainer> errorHandlersIterator) {
        this.handlersIterator = handlersIterator;
        this.errorHandlersIterator = errorHandlersIterator;
    }

    void run(Request request, Response response) {
        if (handlersIterator.hasNext()) {
            Handler handler = handlersIterator.next().getHandler();

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
            ErrorHandler errorHandler = errorHandlersIterator.next().getHandler();

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
