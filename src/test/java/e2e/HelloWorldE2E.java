package e2e;

import com.express.Application;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldE2E {
    @Test
    void express_helloWorld() throws IOException {
        Application app = new Application();

        app.get("/", (req, res) -> {
            res.send("Hello, World!");
        });

        app.listen((short) 3000);

        try(CloseableHttpClient httpclient = HttpClients.createDefault()){
            HttpGet request = new HttpGet("http://localhost:3000/");

            try (CloseableHttpResponse response = httpclient.execute(request)) {
                assertEquals(200, response.getStatusLine().getStatusCode());
                assertEquals("Hello, World!", new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8));
            }
        }
    }
}
