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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestIT {
    static private Application app = new Application();
    static private final HttpClient httpClient = HttpClients.createDefault();

    @BeforeAll
    static void setup() throws IOException {
        app = new Application();

        app.get("/getMethod", (req, res, next) -> res.send(req.getMethod().toString()));
        app.get("/getParam/:p", (req, res, next) -> res.send(req.getParam("p").get()));
        app.get("/getParamChain/:p", (req, res, next) -> next.ok())
                .get("/getParamChain/:z", (req, res, next) -> {
                    final Optional<String> p = req.getParam("p");
                    final Optional<String> z = req.getParam("z");

                    final String result = (p.isPresent() ? "NOT_OK" : "") + (z.orElse("NOT_OK"));
                    res.send(result);
                });
        app.get("/getParamErrorChain/:p", (req, res, next) -> next.error(new RuntimeException("Oops")))
                .get("/getParamErrorChain/:z", (err, req, res, next) -> {
                    final Optional<String> p = req.getParam("p");
                    final Optional<String> z = req.getParam("z");

                    final String result = (p.isPresent() ? "NOT_OK" : "") + (z.orElse("NOT_OK"));
                    res.send(result);
                });


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

    @Test
    void getParam() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/getParam/value");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("value", body);
    }

    @Test
    void getParam_chain() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/getParamChain/value");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("value", body);
    }

    @Test
    void getParam_errorChain() throws IOException {
        HttpGet request = new HttpGet("http://localhost:3000/getParamErrorChain/value");

        HttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals("value", body);
    }
}
