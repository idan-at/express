package com.express.router;

import com.express.handlerfunctions.RequestResponseHandler;

public interface Router {
    Router get(String path, RequestResponseHandler handler);
}
