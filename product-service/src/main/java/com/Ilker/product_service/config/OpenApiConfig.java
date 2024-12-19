package com.Ilker.product_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productServiceOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("Product Service")
                        .description("RestAPI for product service.")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")));
    }
}

