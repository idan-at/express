package com.express.core.handler;

import com.express.api.Handler;
import com.express.http.HttpMethod;

public class HandlerContainer {
    private final Handler handler;
    private final HandlerContext context;

    public HandlerContainer(HttpMethod method, String pattern, Handler handler) {
        context = new HandlerContext(method, pattern);
        this.handler = handler;
    }

    public Handler getHandler() { return handler; }

    public HandlerContext getContext() {
        return context;
    }
}
