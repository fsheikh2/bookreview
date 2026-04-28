# Book Review API

A RESTful API built with Spring Boot that allows users to create books and add reviews. 

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Maven

## Architecture

Controller → Service → Repository → Database

## Getting Started

1. Clone the repo:
   git clone https://github.com/fsheikh2/bookreview

2. Run the application:
   ./mvnw spring-boot:run

3. App runs on:
   http://localhost:8080

## API Endpoints

### Create Book
POST /books

### Get All Books
GET /books

### Get Book by ID
GET /books/{id}

### Add Review
POST /books/{id}/reviews

## Postman Collection

Import the collection located in:
postman/BookReviewCollection.json

This contains ready-to-use API requests.
