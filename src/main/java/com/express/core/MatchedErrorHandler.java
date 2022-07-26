package com.express.core;

import com.express.api.ErrorHandler;
import com.express.api.Handler;
import com.express.core.handler.HandlerMatchResult;

public class MatchedErrorHandler {
    private final ErrorHandler handler;
    private final HandlerMatchResult matchResult;

    MatchedErrorHandler(ErrorHandler handler, HandlerMatchResult matchResult) {
        this.handler = handler;
        this.matchResult = matchResult;
    }

    public ErrorHandler getHandler() {
        return handler;
    }

    public HandlerMatchResult getMatchResult() {
        return matchResult;
    }
}
