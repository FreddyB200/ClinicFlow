package com.medical.appointments.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration validator that ensures all required environment variables are set
 * when running in production profile.
 * 
 * This class performs validation of critical environment variables needed for
 * database connectivity and other production configurations.
 * 
 * @author Freddy Johan Bautista Baquero
 * @version 1.0.0
 */
@Configuration
@Profile("prod")
public class ConfigValidator {
    
    /**
     * Validates that all required environment variables are set in the production environment.
     * This method is automatically called after the application context is initialized.
     * 
     * Required environment variables:
     * - JDBC_DATABASE_URL: The URL for the database connection
     * - JDBC_DATABASE_USERNAME: The database username
     * - JDBC_DATABASE_PASSWORD: The database password
     * 
     * @throws IllegalStateException if any required environment variable is missing
     */
    @PostConstruct
    public void validateConfigVars() {
        List<String> missingVars = new ArrayList<>();
        
        if (System.getenv("JDBC_DATABASE_URL") == null) missingVars.add("JDBC_DATABASE_URL");
        if (System.getenv("JDBC_DATABASE_USERNAME") == null) missingVars.add("JDBC_DATABASE_USERNAME");
        if (System.getenv("JDBC_DATABASE_PASSWORD") == null) missingVars.add("JDBC_DATABASE_PASSWORD");
        
        if (!missingVars.isEmpty()) {
            throw new IllegalStateException(
                "Missing required environment variables for PRODUCTION profile: " + String.join(", ", missingVars)
            );
        }
        
        System.out.println("Production configuration validated successfully!");
    }
} 