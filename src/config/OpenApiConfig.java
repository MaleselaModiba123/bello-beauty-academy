package config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// configures the title and description shown at the top of the Swagger UI
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI belloBeautyOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bello Beauty Academy API")
                        .description("REST API for managing students, courses, and enrollments on the Bello Beauty Academy Platform.")
                        .version("1.0.0"));
    }
}