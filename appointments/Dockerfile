FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the POM file first to leverage Docker cache
COPY pom.xml .
# Copy the source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Install curl and netcat for healthchecks
RUN apk add --no-cache curl netcat-openbsd

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Environment variables with defaults that can be overridden
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseG1GC -Djava.security.egd=file:/dev/./urandom"

# Expose the application port
EXPOSE 8080

# Health check to ensure application is running
HEALTHCHECK --interval=30s --timeout=3s --retries=3 CMD curl -f http://localhost:8080/actuator/health || exit 1

# Set an entrypoint to run the application
ENTRYPOINT ["/bin/sh", "-c", "while ! nc -z db 5432; do echo 'Waiting for database to be available...'; sleep 1; done; echo 'Database is available, starting application...'; java $JAVA_OPTS -jar app.jar"]