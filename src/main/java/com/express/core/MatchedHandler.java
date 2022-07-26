package com.express.core;

import com.express.api.Handler;
import com.express.core.handler.HandlerMatchResult;

public class MatchedHandler {
    private final Handler handler;
    private final HandlerMatchResult matchResult;

    MatchedHandler(Handler handler, HandlerMatchResult matchResult) {
        this.handler = handler;
        this.matchResult = matchResult;
    }

    public Handler getHandler() {
        return handler;
    }

    public HandlerMatchResult getMatchResult() {
        return matchResult;
    }
}
