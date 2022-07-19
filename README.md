# express

Provides an [expressjs](https://expressjs.com/) like API for Java.

```java
import com.express.Application;

class App {
    public static void main(String[] args) throws IOException {
        Application app = new Application();

        app.get("/", (req, res, next) -> {
            res.send("Hello, World!");
        });

        app.listen(3000);
    }
}
```

## Installation

Add this to your `pom.xml` / `build.gradle` file:

```bash
com.express:express:1.0-SNAPSHOT
```

## Examples

