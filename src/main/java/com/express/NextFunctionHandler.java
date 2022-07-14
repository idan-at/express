package com.express;

public interface NextFunctionHandler {
    /**
     * Proceeds with the next handler
     */
    void ok();

    /**
     * Proceeds with the next (or starts with the first) error handler.
     * @param e The exception that will be passed to the ErrorHandler.
     */
    void error(Exception e);
}
