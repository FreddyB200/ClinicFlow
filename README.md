# Medical Appointments Management System

A complete system for managing medical appointments, doctors, and patients.

## Features

- Doctor management
- Medical appointment scheduling
- Patient registration
- Authentication and authorization system
- Modern and responsive web interface

## Requirements

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 13+
- Docker and Docker Compose (optional, for containerized deployment)

## Development Setup

### Prerequisites

Make sure you have the following installed:
1. JDK 17
2. Maven
3. PostgreSQL
4. Git

### Clone the Repository

```bash
git clone https://github.com/yourusername/medical-appointments-system.git
cd medical-appointments-system
```

### Database Configuration

1. Create a PostgreSQL database:
```sql
CREATE DATABASE medical_appointments;
```

2. Configure the connection details in `application-dev.properties` or use environment variables.

### Run the Application

```bash
mvn spring-boot:run
```

The application will be available at: http://localhost:8080/api

## Docker Deployment

The project includes Docker configuration for easy deployment:

```bash
# Copy and configure environment variables
cp .env.example .env
# Edit the .env file with your desired configuration

# Start the containers
docker-compose up -d
```

The application will be available at: http://localhost:9090/api
The database administration panel (pgAdmin) at: http://localhost:5050

## API Documentation

API documentation is available via Swagger UI: http://localhost:8080/api/swagger-ui.html

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. 