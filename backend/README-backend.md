# Backend README

## Setup
- Install Java 11+, Maven.
- Run `mvn clean install` to build.
- Run `mvn spring-boot:run` to start.

## Database
- H2 in-memory: Access console at /h2-console.
- For production, use mysql-schema.sql to create tables in MySQL, update application.properties.

## APIs
Use Postman collection in documentation.