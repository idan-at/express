package com.express;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application implements Routable, AutoCloseable {
    private final RequestHandler requestHandler = new RequestHandler();
    private HttpServer server;

    /**
     * Creates a new application. This does not mean the server starts.
     * See {@link #listen(int port) listen} for actually starting the server.
     */
    public Application() {}

    public Routable get(String pattern, Handler handler) {
        requestHandler.add(new HandlerContainer(HttpMethod.GET, pattern, handler));

        return this;
    }

    @Override
    public Routable post(String pattern, Handler handler) {
        requestHandler.add(new HandlerContainer(HttpMethod.POST, pattern, handler));

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
