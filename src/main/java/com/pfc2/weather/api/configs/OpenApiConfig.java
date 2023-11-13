package com.pfc2.weather.api.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Config for Open Api, security with Bearer token.
 * */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .security(Arrays.asList(
                        new SecurityRequirement().addList("bearerAuth")))
                .info(new Info()
                        .title("Weather API")
                        .description("Weather API RESTful service using OpenAPI 3.")
                        .termsOfService("terms")
                        .contact(new Contact().email("kevin.j03@hotmail.com").name("Developer: Joel Chamorro"))
                        .license(new License().name("GNU"))
                        .version("1.0.0")
                );
    }
}
