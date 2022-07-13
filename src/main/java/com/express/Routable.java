package com.express;

public interface Routable {
    Routable get(String pattern, Handler handler);

    Routable post(String pattern, Handler handler);
}
