# 🛒 E-Commerce Backend (Spring Boot)

## 📌 Overview
This is a full-stack eCommerce backend application built using Spring Boot. It provides secure REST APIs with role-based JWT authentication and supports complete eCommerce functionality like product management, cart, orders, and payments.

---

## 🚀 Features
- 🔐 Role-Based JWT Authentication (User/Admin)
- 📦 Product Management (CRUD)
- 🛒 Cart Functionality
- 📑 Order & Order Items Management
- 💳 Payment Integration
- 🔄 RESTful APIs
- 🗄️ MySQL Database Integration
- ⚡ Spring Data JPA & Hibernate

---

## 🛠️ Tech Stack
- Java
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- Hibernate
- MySQL
- Maven

---

## 📂 Project Structure
Backend/
├── controller/
├── service/
├── repository/
├── model/
├── security/
└── config/



---

## ⚙️ Setup & Run

### 1. Clone the Repository
git clone https://github.com/hardik-kaushik07/ecommerce-project-2.git


### 2. Navigate to Project

### 3. Configure Database
Update `application.properties`:

spring.datasource.url=jdbc:mysql://localhost:3306/springbootproject
spring.datasource.username=root
spring.datasource.password=Hardik@123


### 4. Run the Application
mvn spring-boot:run


---

## 🔑 API Authentication
- Uses JWT Token-based authentication
- Roles: `USER`, `ADMIN`
- Secure endpoints using Spring Security

---

## 📬 API Testing
Use tools like **Postman** to test APIs.

---

## 📈 Future Improvements
- Frontend Integration (React)
- Order Tracking System
- Payment Gateway Enhancement
- Docker Deployment

---

## 👨‍💻 Author
**Hardik Kaushik**

---

## ⭐ Show Your Support
If you like this project, give it a ⭐ on GitHub!
