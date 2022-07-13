package com.express;

public interface Routable {
    Routable get(String pattern, Handler handler);
}
