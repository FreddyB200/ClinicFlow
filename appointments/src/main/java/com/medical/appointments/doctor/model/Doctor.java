package com.medical.appointments.doctor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Entity representing a medical doctor in the system.
 * 
 * This entity maps to the doctors table in the database and contains
 * all the information related to a doctor including personal details,
 * specialties, and contact information.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
@Entity
@Table(name = "doctors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    /**
     * The unique identifier for the doctor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The doctor's first name.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * The doctor's last name.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * The doctor's email address.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * The doctor's phone number.
     */
    private String phone;

    /**
     * The doctor's medical specialty.
     */
    @Column(nullable = false)
    private String specialty;

    /**
     * The doctor's medical license number.
     */
    @Column(name = "license_number", unique = true, nullable = false)
    private String licenseNumber;

    /**
     * The doctor's date of birth.
     */
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * Indicates whether the doctor is currently active in the system.
     */
    private boolean active;

    /**
     * The timestamp when the doctor record was created.
     */
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    /**
     * The timestamp when the doctor record was last updated.
     */
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    /**
     * Sets the creation and update timestamps before persisting a new doctor.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    /**
     * Updates the update timestamp before updating a doctor.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = ZonedDateTime.now();
    }
} 