# Shopping Cart — Spring Boot REST API

A backend e-commerce demonstration project built with Java and Spring Boot. It provides REST endpoints for managing products, categories, images, customer accounts, shopping carts, and orders.

## Features

- Product, category, and product-image management
- Shopping cart and cart-item operations
- Customer orders and order-item management
- User accounts and roles
- JWT-based authentication with Spring Security
- Structured DTOs, request/response models, and exception handling

## Technology Stack

- Java 17, Spring Boot 3.4.4
- Spring Web, Spring Security, Spring Data JPA
- PostgreSQL
- JWT (JJWT)
- Maven

## Project Structure

```text
src/main/java/com/dailycodework/dreamshops/
├── controller/       # REST API endpoints
├── service/          # Business logic
├── model/            # JPA entities
├── repositories/     # Data access
├── security/         # Authentication and JWT filters
├── dto/              # Data transfer objects
├── request/          # API request payloads
└── response/         # API responses
```

## Getting Started

1. Install Java 17 and PostgreSQL.
2. Clone the repository:

```bash
git clone https://github.com/ikraammel/Shopping-Cart.git
cd Shopping-Cart
```

3. Set up a PostgreSQL database and configure database credentials and a JWT signing secret securely for your local environment.
4. Start the backend:

```bash
./mvnw spring-boot:run
```

On Windows, use `mvnw.cmd spring-boot:run`.

The API uses the `/api/v1` prefix.

> **Security note:** Avoid committing database passwords or JWT secrets. Replace any exposed credentials before deployment.
