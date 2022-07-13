package com.express;

public interface Routable {
    /**
     * Routes HTTP GET requests to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The Handler function
     * @return The routable object, to allow chaining.
     */
    Routable get(String pattern, Handler handler);

    /**
     * Routes HTTP POST requests to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The Handler function
     * @return The routable object, to allow chaining.
     */
    Routable post(String pattern, Handler handler);

    /**
     * Routes HTTP PUT requests to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The Handler function
     * @return The routable object, to allow chaining.
     */
    Routable put(String pattern, Handler handler);

    /**
     * Routes HTTP DELETE requests to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The Handler function
     * @return The routable object, to allow chaining.
     */
    Routable delete(String pattern, Handler handler);
}
