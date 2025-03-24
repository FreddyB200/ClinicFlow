package com.medical.appointments.doctor.repository;

import com.medical.appointments.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Doctor entities.
 * 
 * This interface provides methods to interact with the doctor data in the database.
 * It extends JpaRepository to inherit basic CRUD operations and adds custom queries
 * specific to the doctor domain.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    /**
     * Finds a doctor by their email.
     * 
     * @param email The doctor's email to search for
     * @return An Optional containing the found doctor or empty if not found
     */
    Optional<Doctor> findByEmail(String email);
    
    /**
     * Finds a doctor by their license number.
     * 
     * @param licenseNumber The doctor's license number to search for
     * @return An Optional containing the found doctor or empty if not found
     */
    Optional<Doctor> findByLicenseNumber(String licenseNumber);
    
    /**
     * Checks if a doctor exists with the given email.
     * 
     * @param email The email to check
     * @return True if a doctor with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);
    
    /**
     * Checks if a doctor exists with the given license number.
     * 
     * @param licenseNumber The license number to check
     * @return True if a doctor with the given license number exists, false otherwise
     */
    boolean existsByLicenseNumber(String licenseNumber);
    
    /**
     * Finds all doctors by specialty.
     * 
     * @param specialty The specialty to search for
     * @return A list of doctors with the given specialty
     */
    List<Doctor> findBySpecialty(String specialty);
    
    /**
     * Finds all active doctors.
     * 
     * @return A list of all active doctors
     */
    List<Doctor> findByActiveTrue();
    
    /**
     * Searches for doctors by first name or last name (case insensitive).
     * 
     * @param name The name to search for
     * @return A list of doctors whose first or last name contains the search term
     */
    @Query("SELECT d FROM Doctor d WHERE LOWER(d.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(d.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Doctor> searchByName(@Param("name") String name);
} 