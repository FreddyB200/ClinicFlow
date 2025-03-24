package com.medical.appointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a resource already exists.
 * 
 * This exception is used to indicate that a resource with the same unique
 * identifier already exists in the system. It is mapped to a 409 Conflict HTTP status.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {
    
    /**
     * Constructs a new ResourceAlreadyExistsException with the specified detail message.
     * 
     * @param message The detail message
     */
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ResourceAlreadyExistsException for a specific resource type and identifier.
     * 
     * @param resourceName The name of the resource type
     * @param fieldName The name of the identifier field
     * @param fieldValue The value of the identifier
     * @return A new ResourceAlreadyExistsException with a formatted message
     */
    public static ResourceAlreadyExistsException create(String resourceName, String fieldName, Object fieldValue) {
        return new ResourceAlreadyExistsException(
            String.format("%s already exists with %s: '%s'", resourceName, fieldName, fieldValue)
        );
    }
} 