package com.medical.appointments.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("prod")
public class ConfigValidator {
    
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