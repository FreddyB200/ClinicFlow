package com.medical.appointments.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Data Transfer Object for Doctor entities.
 * 
 * This DTO is used to expose doctor data to API clients.
 * It contains all the fields that should be visible in API responses.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    
    /**
     * The unique identifier for the doctor.
     */
    private Long id;
    
    /**
     * The doctor's first name.
     */
    private String firstName;
    
    /**
     * The doctor's last name.
     */
    private String lastName;
    
    /**
     * The doctor's email address.
     */
    private String email;
    
    /**
     * The doctor's phone number.
     */
    private String phone;
    
    /**
     * The doctor's medical specialty.
     */
    private String specialty;
    
    /**
     * The doctor's medical license number.
     */
    private String licenseNumber;
    
    /**
     * The doctor's date of birth.
     */
    private LocalDate dateOfBirth;
    
    /**
     * Indicates whether the doctor is currently active in the system.
     */
    private boolean active;
    
    /**
     * The timestamp when the doctor record was created.
     */
    private ZonedDateTime createdAt;
    
    /**
     * The timestamp when the doctor record was last updated.
     */
    private ZonedDateTime updatedAt;
} 