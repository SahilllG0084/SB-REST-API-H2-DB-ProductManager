package com.cjc.app.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Product Management REST API",
                version = "1.0",
                description = "Spring Boot REST API Documentation"
        )
)
public class OpenApiConfig {
}
