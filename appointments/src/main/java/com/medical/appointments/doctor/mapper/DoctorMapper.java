package com.medical.appointments.doctor.mapper;

import com.medical.appointments.doctor.dto.DoctorDTO;
import com.medical.appointments.doctor.dto.DoctorRequest;
import com.medical.appointments.doctor.model.Doctor;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Doctor entities and DTOs.
 * 
 * This class provides methods to convert Doctor entities to DTOs and vice versa.
 * It is used by the service and controller layers to transform data between
 * persistence and API representation.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
@Component
public class DoctorMapper {

    /**
     * Converts a Doctor entity to a DoctorDTO.
     * 
     * @param doctor The Doctor entity to convert
     * @return The corresponding DoctorDTO
     */
    public DoctorDTO toDTO(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        
        return DoctorDTO.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .email(doctor.getEmail())
                .phone(doctor.getPhone())
                .specialty(doctor.getSpecialty())
                .licenseNumber(doctor.getLicenseNumber())
                .dateOfBirth(doctor.getDateOfBirth())
                .active(doctor.isActive())
                .createdAt(doctor.getCreatedAt())
                .updatedAt(doctor.getUpdatedAt())
                .build();
    }
    
    /**
     * Converts a DoctorRequest to a Doctor entity.
     * This is used for creating new doctors.
     * 
     * @param request The DoctorRequest to convert
     * @return The corresponding Doctor entity
     */
    public Doctor toEntity(DoctorRequest request) {
        if (request == null) {
            return null;
        }
        
        return Doctor.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .specialty(request.getSpecialty())
                .licenseNumber(request.getLicenseNumber())
                .dateOfBirth(request.getDateOfBirth())
                .active(request.isActive())
                .build();
    }
    
    /**
     * Updates an existing Doctor entity with data from a DoctorRequest.
     * 
     * @param doctor The Doctor entity to update
     * @param request The DoctorRequest containing the new data
     */
    public void updateEntityFromRequest(Doctor doctor, DoctorRequest request) {
        if (doctor == null || request == null) {
            return;
        }
        
        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setEmail(request.getEmail());
        doctor.setPhone(request.getPhone());
        doctor.setSpecialty(request.getSpecialty());
        doctor.setLicenseNumber(request.getLicenseNumber());
        doctor.setDateOfBirth(request.getDateOfBirth());
        doctor.setActive(request.isActive());
    }
} 