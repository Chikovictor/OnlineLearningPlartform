package com.example.onlinelearningplatform;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication: This annotation marks the class as the starting point of the Spring Boot app.
// It enables auto-configuration (Spring sets up beans automatically), component scanning (finds @Controller, @Entity, etc.),
// and defines this as a configuration class.
@SpringBootApplication
public class OnlineLearningPlatformApplication {

    // main method: The entry point of any Java application. Here, it runs the Spring Boot app.
    // SpringApplication.run(): Starts the Spring context, loads configs, and launches the embedded server (Tomcat by default).
    public static void main(String[] args) {
        SpringApplication.run(OnlineLearningPlatformApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Allow all APIs
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}