package com.express.handlerfunctions;

import com.express.Request;
import com.express.Response;

@FunctionalInterface
public interface RequestResponseHandler extends Handler {
    void handle(Request request, Response response);
}
