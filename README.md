# Book Tracker

A backend application for managing a book collection, allowing users to browse books, add reviews, and rate them.  
Built with Java Spring Boot featuring JWT authentication, user roles, PostgreSQL database, and unit tests.

---

## Features

- 🔐 **JWT Authentication and Authorization**  
- 👤 **User Roles:** `USER` and `ADMIN`  
- 📚 **Book Management:**  
  - Add, edit, and delete books (ADMIN only)  
  - Retrieve list of books and book details  
- ✍️ **Book Reviews:**  
  - Users can add, update, and delete their own reviews  
- ⭐ **Book Ratings:**  
  - Star rating system (1–5)  
  - Calculation of average book rating  
- 🛠️ **Global Exception Handling:**  
  - Unified API error responses with proper HTTP status codes  
- 🗄️ **PostgreSQL database**  
- 📜 **API Documentation with Swagger (OpenAPI)**  
- 🧪 **Unit Tests:**  
  - Covering `AdminBookService` and exception handling  
- 🔄 **DTOs and Mappers:**  
  - Use of Data Transfer Objects to separate API layer from entities  
  - Mapper classes to convert between DTOs and JPA entities  

---

## Technologies

- Java 17  
- Spring Boot (Security, Data JPA, Web)  
- PostgreSQL  
- JWT (JSON Web Token)  
- Swagger (springdoc-openapi)  
- JUnit 5, Mockito  

---

## Installation and Running

1. Set up PostgreSQL and create a database (e.g., `book_tracker_db`).  
2. Configure database connection and JWT properties in `application.properties`.  
3. Run the application with:

```bash
mvn clean spring-boot:run
