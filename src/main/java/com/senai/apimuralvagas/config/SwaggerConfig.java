package com.senai.apimuralvagas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Mural Vagas")
                        .description("Documentação da API para o projeto Mural Vagas")
                        .contact(new Contact()
                                .url("https://github.com/GuilhermePiDev/APl-vagas-senai")));
    }

}
