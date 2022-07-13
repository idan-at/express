package it;

import com.express.Application;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationIT {
    @Test
    void get_helloWorld() throws IOException {
        try(Application app = new Application()) {
            app.get("/", (req, res, next) -> {
                res.send("Hello, World!");
            });

            app.listen(3000);

            try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpGet request = new HttpGet("http://localhost:3000/");

                try (CloseableHttpResponse response = httpclient.execute(request)) {
                    String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

                    assertEquals(200, response.getStatusLine().getStatusCode());
                    assertEquals("Hello, World!", body);
                }
            }
        }
    }

    @Test
    void get_chain() throws IOException {
        try(Application app = new Application()) {
            app.get("/", (req, res, next) -> {
                next.ok();
            }).get("/", (req, res, next) -> {
                res.send(req.getMethod() + ": Hello, World!");
            });

            app.listen(3000);

            try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpGet request = new HttpGet("http://localhost:3000/");

                try (CloseableHttpResponse response = httpclient.execute(request)) {
                    String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

                    assertEquals(200, response.getStatusLine().getStatusCode());
                    assertEquals("GET: Hello, World!", body);
                }
            }
        }
    }

    @Test
    void post_helloWorld() throws IOException {
        try(Application app = new Application()) {
            app.post("/hello", (req, res, next) -> {
                res.send("Hello, World!");
            });

            app.listen(3000);

            try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpPost request = new HttpPost("http://localhost:3000/hello");

                try (CloseableHttpResponse response = httpclient.execute(request)) {
                    String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

                    assertEquals(200, response.getStatusLine().getStatusCode());
                    assertEquals("Hello, World!", body);
                }
            }
        }
    }

    @Test
    void put_helloWorld() throws IOException {
        try(Application app = new Application()) {
            app.put("/hello", (req, res, next) -> {
                res.send("Hello, World!");
            });

            app.listen(3000);

            try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpPut request = new HttpPut("http://localhost:3000/hello");

                try (CloseableHttpResponse response = httpclient.execute(request)) {
                    String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

                    assertEquals(200, response.getStatusLine().getStatusCode());
                    assertEquals("Hello, World!", body);
                }
            }
        }
    }
}
