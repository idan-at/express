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
     * Routes HTTP GET requests with exceptions to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The ErrorHandler function
     * @return The routable object, to allow chaining.
     */
    Routable get(String pattern, ErrorHandler handler);

    /**
     * Routes HTTP POST requests to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The Handler function
     * @return The routable object, to allow chaining.
     */
    Routable post(String pattern, Handler handler);

    /**
     * Routes HTTP POST requests with exceptions to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The ErrorHandler function
     * @return The routable object, to allow chaining.
     */
    Routable post(String pattern, ErrorHandler handler);

    /**
     * Routes HTTP PUT requests to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The Handler function
     * @return The routable object, to allow chaining.
     */
    Routable put(String pattern, Handler handler);

    /**
     * Routes HTTP PUT requests with exceptions to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The ErrorHandler function
     * @return The routable object, to allow chaining.
     */
    Routable put(String pattern, ErrorHandler handler);

    /**
     * Routes HTTP PATCH requests to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The Handler function
     * @return The routable object, to allow chaining.
     */
    Routable patch(String pattern, Handler handler);

    /**
     * Routes HTTP PATCH requests with exceptions to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The ErrorHandler function
     * @return The routable object, to allow chaining.
     */
    Routable patch(String pattern, ErrorHandler handler);

    /**
     * Routes HTTP DELETE requests to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The Handler function
     * @return The routable object, to allow chaining.
     */
    Routable delete(String pattern, Handler handler);

    /**
     * Routes HTTP DELETE requests with exceptions to the given handler.
     * @param pattern The URL pattern, e.g "/users"
     * @param handler The ErrorHandler function
     * @return The routable object, to allow chaining.
     */
    Routable delete(String pattern, ErrorHandler handler);
}
