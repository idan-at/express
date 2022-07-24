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

public class MiddlewareIT {
    static private Application app = new Application();
    static private final HttpClient httpClient = HttpClients.createDefault();

    @BeforeAll
    static void setup() throws IOException {
        app = new Application();

        app.use("*", (req, res, next) -> {
            res.setStatus(202);
            
            next.ok();
        }).use("*", (req, res, next) -> res.send("From Middleware"));

        app.listen(3000);
    }

    @AfterAll
    static void teardown() {
        app.close();
    }

    @Test
    void get_chain() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/get");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(202, response.getStatusLine().getStatusCode());
        assertEquals("From Middleware", body);
    }

    @Test
    void post_chain() throws IOException {
        HttpPost request = new HttpPost("http://localhost:3000/post");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(202, response.getStatusLine().getStatusCode());
        assertEquals("From Middleware", body);
    }

    @Test
    void patch_chain() throws IOException {
        HttpPatch request = new HttpPatch("http://localhost:3000/patch");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(202, response.getStatusLine().getStatusCode());
        assertEquals("From Middleware", body);
    }

    @Test
    void put_chain() throws IOException {
        HttpPut request = new HttpPut("http://localhost:3000/put");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(202, response.getStatusLine().getStatusCode());
        assertEquals("From Middleware", body);
    }

    @Test
    void delete_chain() throws IOException {
        HttpDelete request = new HttpDelete("http://localhost:3000/delete");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(202, response.getStatusLine().getStatusCode());
        assertEquals("From Middleware", body);
    }
}
