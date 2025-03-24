package com.medical.appointments.doctor.service;

import com.medical.appointments.doctor.dto.DoctorDTO;
import com.medical.appointments.doctor.dto.DoctorRequest;

import java.util.List;

/**
 * Service interface for managing doctors.
 * 
 * This interface defines the contract for doctor-related operations such as
 * creating, retrieving, updating, and deleting doctors.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
public interface DoctorService {
    
    /**
     * Creates a new doctor.
     * 
     * @param doctorRequest The doctor data to create
     * @return The created doctor
     */
    DoctorDTO createDoctor(DoctorRequest doctorRequest);
    
    /**
     * Retrieves a doctor by their ID.
     * 
     * @param id The ID of the doctor to retrieve
     * @return The requested doctor
     */
    DoctorDTO getDoctorById(Long id);
    
    /**
     * Retrieves all doctors.
     * 
     * @return A list of all doctors
     */
    List<DoctorDTO> getAllDoctors();
    
    /**
     * Updates a doctor.
     * 
     * @param id The ID of the doctor to update
     * @param doctorRequest The new doctor data
     * @return The updated doctor
     */
    DoctorDTO updateDoctor(Long id, DoctorRequest doctorRequest);
    
    /**
     * Deletes a doctor.
     * 
     * @param id The ID of the doctor to delete
     */
    void deleteDoctor(Long id);
    
    /**
     * Searches for doctors by name (first name or last name).
     * 
     * @param name The name to search for
     * @return A list of doctors matching the search term
     */
    List<DoctorDTO> searchDoctorsByName(String name);
    
    /**
     * Retrieves doctors by specialty.
     * 
     * @param specialty The specialty to filter by
     * @return A list of doctors with the given specialty
     */
    List<DoctorDTO> getDoctorsBySpecialty(String specialty);
    
    /**
     * Retrieves all active doctors.
     * 
     * @return A list of all active doctors
     */
    List<DoctorDTO> getActiveDoctors();
} 