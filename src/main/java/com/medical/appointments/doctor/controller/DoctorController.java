package com.medical.appointments.doctor.controller;

import com.medical.appointments.doctor.dto.DoctorDTO;
import com.medical.appointments.doctor.dto.DoctorRequest;
import com.medical.appointments.doctor.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing doctors.
 * 
 * This class handles HTTP requests related to doctors, such as
 * creating, retrieving, updating, and deleting doctor records.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
@Tag(name = "Doctor", description = "Doctor management API")
public class DoctorController {
    
    private final DoctorService doctorService;
    
    /**
     * Creates a new doctor.
     * 
     * @param doctorRequest The doctor data to create
     * @return The created doctor
     */
    @PostMapping
    @Operation(summary = "Create a new doctor", description = "Creates a new doctor with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid        @ApiResponse(responseCode = \"201\", description = \"Doctor created successfully\"),\n request data"),
        @ApiResponse(responseCode = "409", description = "Doctor with the same email or license number already exists")
    })
    public ResponseEntity<DoctorDTO> createDoctor(
            @Valid @RequestBody DoctorRequest doctorRequest) {
        
        DoctorDTO createdDoctor = doctorService.createDoctor(doctorRequest);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }
    
    /**
     * Retrieves a doctor by their ID.
     * 
     * @param id The ID of the doctor to retrieve
     * @return The requested doctor
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a doctor by ID", description = "Returns a doctor based on the ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctor found"),
        @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
    public ResponseEntity<DoctorDTO> getDoctorById(
            @Parameter(description = "Doctor ID", required = true)
            @PathVariable Long id) {
        
        DoctorDTO doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }
    
    /**
     * Retrieves all doctors.
     * 
     * @return A list of all doctors
     */
    @GetMapping
    @Operation(summary = "Get all doctors", description = "Returns a list of all doctors")
    @ApiResponse(responseCode = "200", description = "List of doctors retrieved successfully")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }
    
    /**
     * Updates a doctor.
     * 
     * @param id The ID of the doctor to update
     * @param doctorRequest The new doctor data
     * @return The updated doctor
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a doctor", description = "Updates a doctor with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Doctor updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Doctor not found"),
        @ApiResponse(responseCode = "409", description = "Update would result in a duplicate email or license number")
    })
    public ResponseEntity<DoctorDTO> updateDoctor(
            @Parameter(description = "Doctor ID", required = true)
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequest doctorRequest) {
        
        DoctorDTO updatedDoctor = doctorService.updateDoctor(id, doctorRequest);
        return ResponseEntity.ok(updatedDoctor);
    }
    
    /**
     * Deletes a doctor.
     * 
     * @param id The ID of the doctor to delete
     * @return No content response
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a doctor", description = "Deletes a doctor by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Doctor deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
    public ResponseEntity<Void> deleteDoctor(
            @Parameter(description = "Doctor ID", required = true)
            @PathVariable Long id) {
        
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Searches for doctors by name.
     * 
     * @param name The name to search for
     * @return A list of doctors matching the search term
     */
    @GetMapping("/search")
    @Operation(summary = "Search doctors by name", description = "Returns a list of doctors whose name contains the search term")
    @ApiResponse(responseCode = "200", description = "Search results retrieved successfully")
    public ResponseEntity<List<DoctorDTO>> searchDoctorsByName(
            @Parameter(description = "Name to search for", required = true)
            @RequestParam String name) {
        
        List<DoctorDTO> doctors = doctorService.searchDoctorsByName(name);
        return ResponseEntity.ok(doctors);
    }
    
    /**
     * Retrieves doctors by specialty.
     * 
     * @param specialty The specialty to filter by
     * @return A list of doctors with the given specialty
     */
    @GetMapping("/specialty/{specialty}")
    @Operation(summary = "Get doctors by specialty", description = "Returns a list of doctors with the specified specialty")
    @ApiResponse(responseCode = "200", description = "List of doctors retrieved successfully")
    public ResponseEntity<List<DoctorDTO>> getDoctorsBySpecialty(
            @Parameter(description = "Doctor specialty", required = true)
            @PathVariable String specialty) {
        
        List<DoctorDTO> doctors = doctorService.getDoctorsBySpecialty(specialty);
        return ResponseEntity.ok(doctors);
    }
    
    /**
     * Retrieves all active doctors.
     * 
     * @return A list of all active doctors
     */
    @GetMapping("/active")
    @Operation(summary = "Get all active doctors", description = "Returns a list of all active doctors")
    @ApiResponse(responseCode = "200", description = "List of active doctors retrieved successfully")
    public ResponseEntity<List<DoctorDTO>> getActiveDoctors() {
        List<DoctorDTO> doctors = doctorService.getActiveDoctors();
        return ResponseEntity.ok(doctors);
    }
} 