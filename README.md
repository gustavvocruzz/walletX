# 💳 WalletX - Digital Wallet Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.16-green?style=flat-square&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-12%2B-blue?style=flat-square&logo=postgresql)
![API Documentation](https://img.shields.io/badge/API%20Docs-Swagger%2FOpenAPI-yellowgreen?style=flat-square)
![License](https://img.shields.io/badge/License-Apache%202.0-red?style=flat-square)

A modern, production-ready digital wallet management system API built with Spring Boot and fully documented with Swagger/OpenAPI 3.0.

</div>

---

## 🚀 Features

- ✅ **RESTful API** - Clean, well-designed REST endpoints
- ✅ **Interactive API Docs** - Swagger UI with try-it-out functionality
- ✅ **Auto-Generated Documentation** - Never out of sync with code
- ✅ **User Management** - Create, retrieve, and manage users
- ✅ **Data Validation** - Comprehensive input validation with meaningful errors
- ✅ **Security** - Spring Security integration with BCrypt password hashing
- ✅ **Database Migrations** - Flyway for version control of database schema
- ✅ **Professional Architecture** - Clean separation of concerns (Controller → Service → Repository)
- ✅ **Error Handling** - Centralized exception handling with detailed error responses
- ✅ **Production Ready** - Following industry best practices

---

## ⚡ Quick Start

### Prerequisites

- **Java 21** or higher
- **PostgreSQL 12** or higher
- **Maven 3.8** or higher
- **Git**

### Installation & Running

1. **Clone the repository:**
   ```bash
   git clone https://github.com/gustavvocruzz/walletX.git
   cd walletX
   ```

2. **Configure Database:**
   Edit `src/main/resources/application.properties` and update PostgreSQL connection:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/walletx
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   ```

3. **Build the project:**
   ```bash
   ./mvnw clean install
   ```

4. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the application:**
   - 🎨 **Swagger UI**: [http://localhost:8080/api/v1/swagger-ui.html](http://localhost:8080/api/v1/swagger-ui.html)
   - 📄 **OpenAPI JSON**: [http://localhost:8080/api/v1/docs](http://localhost:8080/api/v1/docs)

---

## 📖 API Documentation

### Interactive Swagger UI

Once the application is running, visit the interactive API documentation:

```
http://localhost:8080/api/v1/swagger-ui.html
```

**Features:**
- 🔍 Browse all endpoints with descriptions
- 🧪 Try-it-out functionality to test endpoints directly
- 📋 View request/response schemas
- ✅ See validation rules and constraints
- 💾 Download OpenAPI JSON specification

### Endpoints Overview

#### Users Management

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|------------|
| **POST** | `/users` | Create a new user | 201 Created |
| **GET** | `/users` | Get all users | 200 OK |
| **GET** | `/users/{id}` | Get user by ID | 200 OK |

### Example Requests

#### Create a User

```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "gender": "M",
    "birthday": "1990-01-15",
    "password": "SecurePassword123!",
    "phone": "+55 11 98765-4321",
    "document": "12345678900"
  }'
```

**Success Response (201):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "+55 11 98765-4321",
  "document": "12345678900",
  "status": "ACTIVE",
  "walletId": "550e8400-e29b-41d4-a716-446655440001",
  "createdAt": "2024-01-15T10:30:00"
}
```

#### Get All Users

```bash
curl -X GET http://localhost:8080/users \
  -H "Content-Type: application/json"
```

#### Get User by ID

```bash
curl -X GET http://localhost:8080/users/550e8400-e29b-41d4-a716-446655440000 \
  -H "Content-Type: application/json"
```

### Field Validation Rules

When creating a user, the following rules apply:

| Field | Type | Rules |
|-------|------|-------|
| firstName | String | Required, max 150 characters |
| lastName | String | Required, max 150 characters |
| email | String | Required, valid email format |
| gender | Enum | Required, values: M or F |
| birthday | Date | Required, valid date format (yyyy-MM-dd) |
| password | String | Required, 8-100 characters |
| phone | String | Required, max 30 characters |
| document | String | Required, max 14 characters |

---

## 🛠️ Tech Stack

### Core Framework
- **Spring Boot 3.5.16** - Modern Java framework for building applications
- **Java 21** - Latest Java version with performance improvements
- **Maven 3.8+** - Build and dependency management

### Database & ORM
- **PostgreSQL 12+** - Robust relational database
- **Spring Data JPA** - Object-Relational Mapping
- **Flyway** - Database version control and migrations

### API & Documentation
- **Spring Web** - REST API support
- **Springdoc OpenAPI 2.8.16** - Automatic API documentation generation
- **Swagger UI** - Interactive API exploration and testing

### Security
- **Spring Security** - Authentication and authorization
- **BCrypt** - Password encryption and hashing

### Development Tools
- **Lombok** - Reduce boilerplate code (getters, setters, constructors)
- **Spring Boot DevTools** - Hot reload and faster development

---

## 📁 Project Structure

```
walletX/
├── src/
│   ├── main/
│   │   ├── java/dev/gustavvocruzz/walletX/
│   │   │   ├── config/
│   │   │   │   ├── OpenAPIConfig.java          # Swagger configuration
│   │   │   │   └── SecurityConfig.java         # Security setup
│   │   │   ├── controller/
│   │   │   │   └── UserController.java         # REST endpoints
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   └── WalletService.java
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   ├── Wallet.java
│   │   │   │   ├── Transaction.java
│   │   │   │   └── enums/                      # Gender, Currency, etc.
│   │   │   ├── dtos/
│   │   │   │   ├── request/                    # Request DTOs
│   │   │   │   │   └── UserRequest.java
│   │   │   │   └── response/                   # Response DTOs
│   │   │   │       └── UserResponse.java
│   │   │   ├── mapper/
│   │   │   │   └── UserMapper.java             # DTO ↔ Entity mapping
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── WalletRepository.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java # Centralized error handling
│   │   │   │   └── UserNotFoundException.java
│   │   │   └── WalletXApplication.java         # Main application class
│   │   └── resources/
│   │       ├── db/migration/                   # Flyway migration scripts
│   │       └── application.properties          # Configuration
│   └── test/                                    # Test files
├── pom.xml                                      # Maven configuration
├── README.md                                    # This file
├── API_DOCUMENTATION.md                         # Detailed API docs
├── TESTING_GUIDE.md                             # Testing examples
├── QUICK_START.md                               # Quick reference
└── IMPLEMENTATION_SUMMARY.md                    # Implementation details
```

---

## 🔐 Security

- ✅ CSRF protection configured
- ✅ Password hashing with BCrypt
- ✅ Input validation on all endpoints
- ✅ Spring Security integration
- ✅ Database-level constraints

**Note:** Currently all endpoints are publicly accessible. JWT authentication will be implemented in future versions.

---

## 🧪 Testing the API

### Using Swagger UI
1. Go to [http://localhost:8080/api/v1/swagger-ui.html](http://localhost:8080/api/v1/swagger-ui.html)
2. Click on any endpoint
3. Click "Try it out"
4. Fill in the parameters
5. Click "Execute"

### Using Postman/Insomnia
1. Import OpenAPI spec: `http://localhost:8080/api/v1/docs`
2. All endpoints are automatically imported
3. Start making requests

### Using curl
See [TESTING_GUIDE.md](./TESTING_GUIDE.md) for comprehensive curl examples

---

## 🔄 Development Workflow

### Making Changes

1. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. Make your changes and test:
   ```bash
   ./mvnw clean test
   ```

3. Build and verify:
   ```bash
   ./mvnw clean install
   ```

4. Commit with clear messages:
   ```bash
   git commit -m "feat: add new endpoint description"
   ```

5. Push and create a Pull Request:
   ```bash
   git push origin feature/your-feature-name
   ```

### Code Style
- Follow Java naming conventions
- Use meaningful variable names
- Add Javadoc for public methods
- Keep methods focused and small
- Write tests for new features

---

## 🚧 Roadmap

### Phase 1 (Current) ✅
- [x] User management endpoints
- [x] API documentation with Swagger
- [x] Database setup with Flyway
- [x] Error handling
- [x] Data validation

### Phase 2 (Planned) 🔄
- [ ] JWT Authentication & Authorization
- [ ] Wallet management endpoints
- [ ] Transaction processing
- [ ] Balance management
- [ ] Unit & integration tests

### Phase 3 (Future) 📅
- [ ] Transaction history & reports
- [ ] Notifications system
- [ ] Performance optimization
- [ ] Advanced security features
- [ ] CI/CD pipeline setup

---

## 📞 Support & Contact

- **GitHub Issues**: [Report bugs or suggest features](https://github.com/gustavvocruzz/walletX/issues)
- **GitHub Profile**: [@gustavvocruzz](https://github.com/gustavvocruzz)

---

## 🤝 Contributing

Contributions are welcome! Here's how to contribute:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Before Contributing
- Read the code style guidelines
- Ensure all tests pass
- Update documentation as needed
- Follow commit message conventions

---

## 🎓 Learning Resources

Useful resources for understanding this project:

- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [OpenAPI 3.0 Specification](https://spec.openapis.org/oas/v3.0.3)
- [Springdoc OpenAPI](https://springdoc.org/)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Flyway Database Migrations](https://flywaydb.org/)

---

## 📊 Project Stats

- **Language**: Java 21
- **Framework**: Spring Boot 3.5
- **API Documentation**: Swagger/OpenAPI 3.0
- **Database**: PostgreSQL
- **Build Tool**: Maven
---

## ✨ Key Highlights

- 🏗️ **Clean Architecture** - Separation of concerns with layers (Controller → Service → Repository)
- 📚 **Auto-Generated Docs** - Documentation always stays in sync with code
- 🎨 **Beautiful UI** - Interactive Swagger UI for testing endpoints
- 🔒 **Production Ready** - Built with best practices and security in mind
- 🚀 **Scalable Design** - Ready to grow with new features
- 📝 **Well Documented** - Comprehensive documentation for developers

---

<div align="center">

### 🌟 If you find this project helpful, please give it a star! 🌟

Built with ❤️ for learning and professional development.

© 2026 Gustavo Cruz. All rights reserved.

</div>
