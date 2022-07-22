package it.application;

import com.express.Application;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseIT {
    static private Application app = new Application();
    static private final HttpClient httpClient = HttpClients.createDefault();

    @BeforeAll
    static void setup() throws IOException {
        app = new Application();

        app.get("/send", (req, res, next) -> res.send("Hello, World!"));
        app.get("/setStatus", (req, res, next) -> res.setStatus(202).send("Hello, World!"));
        app.get("/sendStatus", (req, res, next) -> res.sendStatus(400));
        app.get("/setHeader", (req, res, next) -> res.setHeader("X-Custom-Header", "some-value").sendStatus(200));

        app.listen(3000);
    }

    @AfterAll
    static void teardown() {
        app.close();
    }

    @Test
    void send() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/send");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Hello, World!", body);
    }

    @Disabled("Hangs when runs together with setHeader, not sure why yet.")
    @Test
    void setStatus() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/setStatus");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(202, response.getStatusLine().getStatusCode());
        assertEquals("Hello, World!", body);
    }

    @Test
    void sendStatus() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/sendStatus");

        HttpResponse response = httpClient.execute(request);

        assertEquals(400, response.getStatusLine().getStatusCode());
    }

    @Test
    void setHeader() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/setHeader");

        HttpResponse response = httpClient.execute(request);

        assertEquals("some-value", response.getHeaders("X-Custom-Header")[0].getValue());
    }
}
