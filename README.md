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

Add this dependency to your `pom.xml` / `build.gradle` file:

```bash
com.express:express:1.0-SNAPSHOT
```

## API
### Routing

Routing is similar to express js. Routes can be:
- **static**: `/login`
- **wildcard**: `/api/*`
- **include parameters**: `/users/:id`

For example:
```java
import com.express.Application;

class App {
    public static void main(String[] args) throws IOException {
        Application app = new Application();
        
        app.get("/login", (req, res, next) -> res.sendStatus(401))
            .get("/api/*", (req, res, next) -> res.send("{}"))
            .get("/users/:id", (req, res, next) -> res.send(req.getParam("id").get()));

        app.listen(3000);
    }
}
```

## Todo:
- Router
- More Request & Response APIs