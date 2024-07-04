package com.innovix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    // Definindo um bean para configurar CORS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Adiciona mapeamentos CORS para todos os caminhos (/**)
                registry.addMapping("/**")
                        // Permite origens específicas (http://localhost:3000)
                        .allowedOrigins("http://localhost:3000")
                        // Permite métodos HTTP específicos
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // Permite todos os cabeçalhos
                        .allowedHeaders("*")
                        // Permite credenciais
                        .allowCredentials(true);
            }
        };
    }
}
