package com.williamkalogeropoulos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Lending Library API")
                        .description("API for managing book lending in a library system.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Library Support")
                                .email("support@library.com")
                        )
                );
    }
}
