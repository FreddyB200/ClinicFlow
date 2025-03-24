package com.medical.appointments.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an API error response.
 * 
 * This class is used to structure error responses in a consistent format
 * across the API. It includes details about the error, such as status,
 * message, timestamp, and field-specific validation errors if applicable.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
@Data
public class ApiError {
    
    /**
     * The HTTP status code of the error.
     */
    private HttpStatus status;
    
    /**
     * The timestamp when the error occurred.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    
    /**
     * The main error message.
     */
    private String message;
    
    /**
     * The error code (optional).
     */
    private String errorCode;
    
    /**
     * Path of the request that caused the error.
     */
    private String path;
    
    /**
     * List of field-specific validation errors.
     */
    private List<String> errors = new ArrayList<>();
    
    /**
     * Constructs a new ApiError with the specified status.
     * 
     * @param status The HTTP status of the error
     */
    public ApiError(HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }
    
    /**
     * Constructs a new ApiError with the specified status and message.
     * 
     * @param status The HTTP status of the error
     * @param message The error message
     */
    public ApiError(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }
    
    /**
     * Constructs a new ApiError with the specified status, message, and error code.
     * 
     * @param status The HTTP status of the error
     * @param message The error message
     * @param errorCode The error code
     */
    public ApiError(HttpStatus status, String message, String errorCode) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }
    
    /**
     * Adds a field error to the errors list.
     * 
     * @param error The error to add
     */
    public void addError(String error) {
        this.errors.add(error);
    }
} 