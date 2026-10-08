# Fashion E-Commerce — Shopping Cart Backend

Backend REST API for an e-commerce application, associated with a larger fashion-shopping project. This repository contains the Java backend, not the React frontend.

## Features

- Product, category and product-image management
- Shopping cart and cart-item operations
- Order and order-item management
- User accounts and role entities
- JWT authentication with Spring Security
- DTO-based REST API endpoints

## Tech Stack

Java 17 · Spring Boot 3.4.4 · Spring Security · Spring Data JPA · PostgreSQL · JWT · Maven

## Structure

- `controller/` — API endpoints
- `service/` — business logic
- `model/` — JPA entities
- `repositories/` — persistence
- `security/` — JWT and security configuration
- `dto/` — API data transfer objects

## Run Locally

1. Install Java 17 and PostgreSQL.
2. Clone this repository.
3. Create a local PostgreSQL database and configure your database credentials and JWT secret using secure local settings.
4. Start the backend:

```bash
./mvnw spring-boot:run
```

The API is configured with the `/api/v1` prefix.

## Related Project

The wider fashion e-commerce project includes React and OAuth2 according to the project description. Those components are not present in this backend repository.
