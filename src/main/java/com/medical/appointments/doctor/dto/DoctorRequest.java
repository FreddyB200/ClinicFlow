package com.medical.appointments.doctor.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Data Transfer Object for creating or updating Doctor entities.
 * 
 * This DTO is used to receive doctor data from API clients when creating
 * or updating doctor records. It includes validation rules to ensure data integrity.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequest {
    
    /**
     * The doctor's first name.
     * Must not be null or empty and must be between 2 and 50 characters.
     */
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    
    /**
     * The doctor's last name.
     * Must not be null or empty and must be between 2 and 50 characters.
     */
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    
    /**
     * The doctor's email address.
     * Must be a valid email format and must not be null or empty.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;
    
    /**
     * The doctor's phone number.
     * Must be a valid phone number format.
     */
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Phone number must be valid")
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phone;
    
    /**
     * The doctor's medical specialty.
     * Must not be null or empty and must be between 3 and 50 characters.
     */
    @NotBlank(message = "Specialty is required")
    @Size(min = 3, max = 50, message = "Specialty must be between 3 and 50 characters")
    private String specialty;
    
    /**
     * The doctor's medical license number.
     * Must not be null or empty and must be between 5 and 50 characters.
     */
    @NotBlank(message = "License number is required")
    @Size(min = 5, max = 50, message = "License number must be between 5 and 50 characters")
    private String licenseNumber;
    
    /**
     * The doctor's date of birth.
     * Must be in the past.
     */
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    
    /**
     * Indicates whether the doctor is currently active in the system.
     */
    private boolean active = true;
} 