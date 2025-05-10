# ClinicFlow

This project is a platform for managing medical appointments, doctors, patients, users, and specialties. It is designed with a professional architecture using Spring Boot, PostgreSQL, and Docker. The system is structured to be easily extendable and ready for integration with modern frontends and advanced security.

## Main Features
- Doctor, Patient, Appointment, Specialty, and User management
- Role-based authentication and authorization (ready for advanced security)
- RESTful API documented with Swagger/OpenAPI
- MVP web interface using Thymeleaf for rapid prototyping
- PostgreSQL persistence
- Docker and Docker Compose for easy deployment
- Clean, maintainable codebase in English

## Architecture
- **Backend:** Spring Boot 3.4.4 (Java 17)
- **Database:** PostgreSQL
- **Frontend MVP:** Thymeleaf (for demo/testing, ready for migration to SPA)
- **API Docs:** Swagger UI (`/swagger-ui.html`)
- **Containerization:** Docker & Docker Compose

## Requirements
- Java 17+
- Maven 3.6+
- PostgreSQL 13+
- Docker & Docker Compose (optional)

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/medical-appointments-system.git
cd medical-appointments-system
```

### 2. Local Development (without Docker)
- Create a PostgreSQL database:
  ```sql
  CREATE DATABASE medical_appointments;
  ```
- Configure your credentials in `src/main/resources/application-dev.properties` or via environment variables.
- Run the application:
  ```bash
  mvn spring-boot:run
  ```
- Access the MVP web interface at: http://localhost:8080/api (context path `/api`)
- Access Swagger UI at: http://localhost:8080/api/swagger-ui.html

### 3. Docker Deployment
- Copy and configure environment variables:
  ```bash
  cp .env.example .env
  # Edit the .env file with your configuration
  ```
- Build and run with Docker Compose:
  ```bash
  docker-compose up --build
  ```
- The app will be available at: http://localhost:9091/api
- Swagger UI: http://localhost:9091/api/swagger-ui.html
The database administration panel (pgAdmin) at: http://localhost:5050

## API Documentation

API documentation is available via Swagger UI: http://localhost:8080/api/swagger-ui.html

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. 
