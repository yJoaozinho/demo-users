package com.ecommerce.demo_users.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocOpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info().title("REST API - Ecommerce Java")
                                .description("Documentação para o recurso de usúarios.")
                                .version("v1")
                                .contact(new Contact().name("João Venícius").email("jvsm1@discente.ifpe.edu.br"))
                );
    }

}
