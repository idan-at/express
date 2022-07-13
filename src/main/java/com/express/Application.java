package com.express;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application {
    private final Router router = new Router();
    private HttpServer httpServer;

    public Application() {}


    public void get(String path, Handler handler) {
    }

    public void listen(int port) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(port);

        httpServer = HttpServer.create(socketAddress, 0);
        httpServer.createContext("/", router::handle);

        httpServer.start();
    }

    // TODO: Impl auto closable
    public void close() {
        httpServer.stop(0);
    }
}
