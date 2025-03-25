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

# Copy wait-for-it script
COPY src/main/resources/wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

# Environment variables for Spring
ENV SPRING_PROFILES_ACTIVE=prod

# Optimized JVM settings
ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/medical-appointments/ -Djava.security.egd=file:/dev/./urandom"

# Expose the application port
EXPOSE 8080

# Set an entrypoint to run the application
ENTRYPOINT ["/bin/sh", "-c", "/app/wait-for-it.sh db 5432 && java $JAVA_OPTS -jar app.jar"]