package com.express.api;

import com.express.core.Request;
import com.express.core.Response;

@FunctionalInterface
public interface Handler {
    /**
     * The handling function
     * @param request The HTTP request
     * @param response The HTTP response
     * @param next The next function handler, which allows proceeding in the current/error chain.
     */
    void handle(Request request, Response response, NextFunctionHandler next);
}
