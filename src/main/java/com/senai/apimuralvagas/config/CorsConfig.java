package com.senai.apimuralvagas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite CORS em todos os endpoints
                        .allowedOrigins("*") // Substitua com os domínios permitidos
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Métodos HTTP permitidos
                        .allowedHeaders("*") ;// Cabeçalhos permitidos
                       // .allowCredentials(true); // Se você quiser permitir cookies e autenticação
            }
        };
    }
}

