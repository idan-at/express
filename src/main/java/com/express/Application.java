package com.express;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application implements Routable, AutoCloseable {
    private final IncomingHandler incomingHandler = new IncomingHandler();
    private HttpServer httpServer;

    public Application() {}

    public Routable get(String pattern, Handler handler) {
        incomingHandler.add(new HandlerContainer(HttpMethod.GET, pattern, handler));

        return this;
    }

    public void listen(int port) throws IOException {
        final InetSocketAddress socketAddress = new InetSocketAddress(port);

        httpServer = HttpServer.create(socketAddress, 0);
        httpServer.createContext("/", incomingHandler::handle);

        httpServer.start();
    }

    public void close() {
        if (httpServer != null) {
            httpServer.stop(0);
        }
    }
}
