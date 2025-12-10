package com.example.OnlineLearningPlartform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication: This annotation marks the class as the starting point of the Spring Boot app.
// It enables auto-configuration (Spring sets up beans automatically), component scanning (finds @Controller, @Entity, etc.),
// and defines this as a configuration class.
@SpringBootApplication
public class OnlineLearningPlartformApplication {

    // main method: The entry point of any Java application. Here, it runs the Spring Boot app.
    // SpringApplication.run(): Starts the Spring context, loads configs, and launches the embedded server (Tomcat by default).
    public static void main(String[] args) {
        SpringApplication.run(OnlineLearningApplication.class, args);
    }

}