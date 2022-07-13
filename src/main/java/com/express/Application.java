package com.express;
import com.express.handlerfunctions.RequestResponseHandler;
import com.express.router.Router;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application implements Router, AutoCloseable {
    private final IncomingHandler incomingHandler = new IncomingHandler();
    private HttpServer httpServer;

    public Application() {}


    public Router get(String path, RequestResponseHandler handler) {
        return this;
    }

    public void listen(int port) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(port);

        httpServer = HttpServer.create(socketAddress, 0);
        httpServer.createContext("/", incomingHandler::handle);

        httpServer.start();
    }

    public void close() {
        httpServer.stop(0);
    }
}
