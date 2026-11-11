# E-Commerce Product Module — Core Java Case Study

## Purpose
A small educational case study to understand Core Java and OOP concepts. This is intentionally **not** a production e-commerce system: no Spring Boot, database, REST API, real payment gateway, courier integration, or complex architecture.

## Requirements
- JDK 17+
- Maven is optional. You can compile directly with `javac`.

## Run with Java compiler
Open a terminal in this folder:

```bash
javac -d out src/com/ecommerce/*.java
java -cp out com.ecommerce.Main
```

Or compile/run in IntelliJ IDEA or Eclipse by opening the source folder.

## Demo
Seeded products are available immediately. Use menu choices to:
- list/search products
- add items to a cart
- place an order
- view order status and simulated tracking
- try UPI, card, COD, and gift-card payment
- test a subscription plan for free shipping
- test a loyalty discount after eligible delivered-order spend reaches the threshold

## Core Java concepts covered
- Classes and objects
- Encapsulation and data hiding
- Abstraction through interfaces and an abstract class
- Inheritance (`Customer` and `Admin` extend `User`)
- Polymorphism (different `PaymentMethod` implementations)
- Collections (`ArrayList`, `HashMap`)
- Enums for order status and subscription plans
- Exception handling
- `BigDecimal` for money
- `LocalDate` / `LocalDateTime`
- Searching, filtering, loops, and methods

## Important
All data is held in memory and resets on restart. Payment and tracking are simulated. The admin menu is intentionally simple for classroom demonstration, not secure production authorization.
