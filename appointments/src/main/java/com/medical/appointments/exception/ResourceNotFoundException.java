package com.medical.appointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource is not found.
 * 
 * This exception is used to indicate that a requested entity or resource
 * could not be found in the system. It is mapped to a 404 Not Found HTTP status.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     * 
     * @param message The detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ResourceNotFoundException for a specific resource type and identifier.
     * 
     * @param resourceName The name of the resource type
     * @param fieldName The name of the identifier field
     * @param fieldValue The value of the identifier
     * @return A new ResourceNotFoundException with a formatted message
     */
    public static ResourceNotFoundException create(String resourceName, String fieldName, Object fieldValue) {
        return new ResourceNotFoundException(
            String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue)
        );
    }
} 