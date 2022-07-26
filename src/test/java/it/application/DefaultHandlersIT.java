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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultHandlersIT {
    static private Application app = new Application();
    static private final HttpClient httpClient = HttpClients.createDefault();

    @BeforeAll
    static void setup() throws IOException {
        app = new Application();

        app.get("/missing-error-handler", (req, res, next) -> next.error(new RuntimeException("oops")));

        app.listen(3000);
    }

    @AfterAll
    static void teardown() {
        app.close();
    }

    @Test
    void respondsWith404WhenNoMatchingHandler() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/does-not-exist");

        HttpResponse response = httpClient.execute(request);

        assertEquals(404, response.getStatusLine().getStatusCode());
    }

    @Test
    void respondsWith500WhenNoMatchingErrorHandler() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/missing-error-handler");

        HttpResponse response = httpClient.execute(request);

        assertEquals(500, response.getStatusLine().getStatusCode());
    }
}
