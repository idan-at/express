package com.express.core;

import com.express.api.ErrorHandler;
import com.express.api.Handler;
import com.express.api.NextFunctionHandler;
import com.express.core.handler.ErrorHandlerContainer;
import com.express.core.handler.HandlerContainer;

import java.util.Iterator;

public class RequestChain {
    private final Iterator<MatchedHandler> matchedHandlerIterator;
    private final Iterator<MatchedErrorHandler> matchedErrorHandlerIterator;

    RequestChain(Iterator<MatchedHandler> matchedHandlerIterator, Iterator<MatchedErrorHandler> matchedErrorHandlerIterator) {
        this.matchedHandlerIterator = matchedHandlerIterator;
        this.matchedErrorHandlerIterator = matchedErrorHandlerIterator;
    }

    public void run(Request request, Response response) {
        if (matchedHandlerIterator.hasNext()) {
            MatchedHandler matchedHandler = matchedHandlerIterator.next();

            request.setParams(matchedHandler.getMatchResult().getParams());
            matchedHandler.getHandler().handle(request, response, new NextFunctionHandler() {
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
        if (matchedErrorHandlerIterator.hasNext()) {
            MatchedErrorHandler matchedErrorHandler = matchedErrorHandlerIterator.next();

            request.setParams(matchedErrorHandler.getMatchResult().getParams());
            matchedErrorHandler.getHandler().handle(e, request, response, new NextFunctionHandler() {
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
