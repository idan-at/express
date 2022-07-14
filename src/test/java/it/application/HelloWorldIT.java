package it.application;

import com.express.Application;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldIT {
    static private Application app = new Application();
    static private final HttpClient httpClient = HttpClients.createDefault();

    @BeforeAll
    static void setup() throws IOException {
        app = new Application();

        app.get("/get", (req, res, next) -> res.send("Hello, World!"));
        app.post("/post", (req, res, next) -> res.send("Hello, World!"));
        app.patch("/patch", (req, res, next) -> res.send("Hello, World!"));
        app.put("/put", (req, res, next) -> res.send("Hello, World!"));
        app.delete("/delete", (req, res, next) -> res.send("Hello, World!"));

        app.listen(3000);
    }

    @AfterAll
    static void teardown() {
        app.close();
    }

    @Test
    void get_helloWorld() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/get");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Hello, World!", body);
    }

    @Test
    void post_helloWorld() throws IOException {
        HttpPost request = new HttpPost("http://localhost:3000/post");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Hello, World!", body);
    }

    @Test
    void patch_helloWorld() throws IOException {
        HttpPatch request = new HttpPatch("http://localhost:3000/patch");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Hello, World!", body);
    }

    @Test
    void put_helloWorld() throws IOException {
        HttpPut request = new HttpPut("http://localhost:3000/put");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Hello, World!", body);
    }

    @Test
    void delete_helloWorld() throws IOException {
        HttpDelete request = new HttpDelete("http://localhost:3000/delete");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("Hello, World!", body);
    }
}
