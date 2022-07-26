package it.application;

import com.express.Application;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorChainingIT {
    static private Application app = new Application();
    static private final HttpClient httpClient = HttpClients.createDefault();

    @BeforeAll
    static void setup() throws IOException {
        RuntimeException e = new RuntimeException("Oops");

        app = new Application();

        app.get("/get", (req, res, next) -> next.error(e)).get("/get", (err, req, res, next) -> res.send(err.getMessage()));
        app.post("/post", (req, res, next) -> next.error(e)).post("/post", (err, req, res, next) -> res.send(err.getMessage()));
        app.patch("/patch", (req, res, next) -> next.error(e)).patch("/patch", (err, req, res, next) -> res.send(err.getMessage()));
        app.put("/put", (req, res, next) -> next.error(e)).put("/put", (err, req, res, next) -> res.send(err.getMessage()));
        app.delete("/delete", (req, res, next) -> next.error(e)).delete("/delete", (err, req, res, next) -> res.send(err.getMessage()));

        app.get("/disallow-error-to-ok", (req, res, next) -> next.error(e)).get("/disallow-error-to-ok", (err, req, res, next) -> {
            try {
                next.ok();
                res.sendStatus(500);
            } catch (Exception error) {
                res.sendStatus(200);
            }
        });

        app.listen(3000);
    }

    @AfterAll
    static void teardown() {
        app.close();
    }

    @Test
    void get_errorChain() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/get");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Oops", body);
    }

    @Test
    void post_errorChain() throws IOException {
        HttpPost request = new HttpPost("http://localhost:3000/post");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Oops", body);
    }

    @Test
    void patch_errorChain() throws IOException {
        HttpPatch request = new HttpPatch("http://localhost:3000/patch");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Oops", body);
    }

    @Test
    void put_errorChain() throws IOException {
        HttpPut request = new HttpPut("http://localhost:3000/put");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Oops", body);
    }

    @Test
    void delete_errorChain() throws IOException {
        HttpDelete request = new HttpDelete("http://localhost:3000/delete");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Oops", body);
    }

    @Test
    void disallowErrorToInvokeNextOk() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/disallow-error-to-ok");

        HttpResponse response = httpClient.execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());
    }
}
