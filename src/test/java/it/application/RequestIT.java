package it.application;

import com.express.Application;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestIT {
    static private Application app = new Application();
    static private final HttpClient httpClient = HttpClients.createDefault();

    @BeforeAll
    static void setup() throws IOException {
        app = new Application();

        app.get("/getMethod", (req, res, next) -> res.send(req.getMethod().toString()));

        app.listen(3000);
    }

    @AfterAll
    static void teardown() {
        app.close();
    }

    @Test
    void getMethod() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/getMethod");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("GET", body);
    }
}
