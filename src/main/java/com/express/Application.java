package com.express;

import com.express.api.ErrorHandler;
import com.express.api.Handler;
import com.express.api.Routable;
import com.express.core.handler.ErrorHandlerContainer;
import com.express.core.handler.HandlerContainer;
import com.express.core.handler.RequestHandler;
import com.express.http.HttpMethod;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class Application implements Routable, AutoCloseable {
    private final RequestHandler requestHandler = new RequestHandler();
    private final Logger logger;
    private HttpServer server;

    /**
     * Creates a new application. This does not mean the server starts.
     * See {@link #listen(int port) listen} for actually starting the server.
     */
    public Application() {
        logger = Logger.getLogger(Application.class.getName());
    }

    @Override
    public Routable use(String pattern, Handler handler) {
        requestHandler.add(null, pattern, handler);

        return this;
    }

    public Routable get(String pattern, Handler handler) {
        requestHandler.add(HttpMethod.GET, pattern, handler);

        return this;
    }

    @Override
    public Routable get(String pattern, ErrorHandler handler) {
        requestHandler.add(HttpMethod.GET, pattern, handler);

        return this;
    }

    @Override
    public Routable post(String pattern, Handler handler) {
        requestHandler.add(HttpMethod.POST, pattern, handler);

        return this;
    }

    @Override
    public Routable post(String pattern, ErrorHandler handler) {
        requestHandler.add(HttpMethod.POST, pattern, handler);

        return this;
    }

    @Override
    public Routable put(String pattern, Handler handler) {
        requestHandler.add(HttpMethod.PUT, pattern, handler);

        return this;
    }

    @Override
    public Routable put(String pattern, ErrorHandler handler) {
        requestHandler.add(HttpMethod.PUT, pattern, handler);

        return this;
    }

    @Override
    public Routable patch(String pattern, Handler handler) {
        requestHandler.add(HttpMethod.PATCH, pattern, handler);

        return this;
    }

    @Override
    public Routable patch(String pattern, ErrorHandler handler) {
        requestHandler.add(HttpMethod.PATCH, pattern, handler);

        return this;
    }

    @Override
    public Routable delete(String pattern, Handler handler) {
        requestHandler.add(HttpMethod.DELETE, pattern, handler);

        return this;
    }

    @Override
    public Routable delete(String pattern, ErrorHandler handler) {
        requestHandler.add(HttpMethod.DELETE, pattern, handler);

        return this;
    }

    /**
     * Starts the server.
     * @param port The port to listen to
     * @throws IOException
     */
    public void listen(int port) throws IOException {
        final InetSocketAddress socketAddress = new InetSocketAddress(port);

        server = HttpServer.create(socketAddress, 0);
        server.createContext("/", requestHandler::handle);

        logger.info("Starting application...");
        server.start();
    }

    /**
     * Closes the server.<br>
     * Does nothing when the server has not been started yet.
     */
    public void close() {
        if (server != null) {
            server.stop(0);
        }
    }
}
