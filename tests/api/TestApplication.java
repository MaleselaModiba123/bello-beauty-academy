package api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// test-only Spring Boot configuration
// this gives @SpringBootTest the @SpringBootConfiguration it needs to start the context
@SpringBootApplication
@ComponentScan(basePackages = {"api", "services", "repositories", "exceptions", "config"})
public class TestApplication {
}