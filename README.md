# 🛡️ User Management API

A secure RESTful API for user management built with **Spring Boot**, **Spring Security**, **JWT**, and **MySQL**.

## 🚀 Features

- User registration and login.
- Stateless authentication using JWT.
- Role-based access control (`USER`, `ADMIN`).
- Protected API endpoints.
- Password hashing with BCrypt.

---

## ⚙️ Requirements

- Java 17+
- Maven
- MySQL
- Postman (for testing)

---

## 🧑‍💻 How to Run the Project

### 1. Clone the repository

git clone https://github.com/your-username/user-management-api.git
cd user-management-api

2. Set up the MySQL database
Create a database named userdb:

sql
CREATE DATABASE userdb;

3. Configure application.properties
Edit the file located at src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/userdb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_super_secret_jwt_key
jwt.expiration=3600000
4. Run the project
./mvnw spring-boot:run
🔐 API Endpoints and Testing
1. 📌 Register a user
POST /api/auth/register

json
{
  "username": "john",
  "password": "12345",
  "role": "USER"
}
2. 🔑 Login
POST /api/auth/login

json
{
  "username": "john",
  "password": "12345"
}
Response:

json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
3. 👤 Get all users (Requires token)
GET /api/users

Headers:

Authorization: Bearer <TOKEN>
4. ❌ Delete a user (ADMIN only)
DELETE /api/users/{id}

🧪 Testing with Postman
A Postman collection file (UserManagementAPI.postman_collection.json) is included in the repository to help you test all API endpoints quickly and easily.

📦 Technologies Used
Spring Boot

Spring Security

JSON Web Token (jjwt)

Spring Data JPA

MySQL

BCrypt

