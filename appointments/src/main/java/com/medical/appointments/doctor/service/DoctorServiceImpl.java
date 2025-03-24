package com.medical.appointments.doctor.service;

import com.medical.appointments.doctor.dto.DoctorDTO;
import com.medical.appointments.doctor.dto.DoctorRequest;
import com.medical.appointments.doctor.mapper.DoctorMapper;
import com.medical.appointments.doctor.model.Doctor;
import com.medical.appointments.doctor.repository.DoctorRepository;
import com.medical.appointments.exception.ResourceAlreadyExistsException;
import com.medical.appointments.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the DoctorService interface.
 * 
 * This class provides the business logic for doctor-related operations.
 * 
 * @author Medical Appointments Team
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    
    /**
     * {@inheritDoc}
     * 
     * @throws ResourceAlreadyExistsException if a doctor with the same email or license number already exists
     */
    @Override
    @Transactional
    @CacheEvict(value = {"doctors", "activeDoctor"}, allEntries = true)
    public DoctorDTO createDoctor(DoctorRequest doctorRequest) {
        // Check if doctor with same email already exists
        if (doctorRepository.existsByEmail(doctorRequest.getEmail())) {
            throw ResourceAlreadyExistsException.create("Doctor", "email", doctorRequest.getEmail());
        }
        
        // Check if doctor with same license number already exists
        if (doctorRepository.existsByLicenseNumber(doctorRequest.getLicenseNumber())) {
            throw ResourceAlreadyExistsException.create("Doctor", "licenseNumber", doctorRequest.getLicenseNumber());
        }
        
        // Convert request to entity and save
        Doctor doctor = doctorMapper.toEntity(doctorRequest);
        Doctor savedDoctor = doctorRepository.save(doctor);
        
        // Convert entity to DTO and return
        return doctorMapper.toDTO(savedDoctor);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @throws ResourceNotFoundException if no doctor is found with the specified ID
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "doctor", key = "#id")
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.create("Doctor", "id", id));
        
        return doctorMapper.toDTO(doctor);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "doctors")
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * {@inheritDoc}
     * 
     * @throws ResourceNotFoundException if no doctor is found with the specified ID
     * @throws ResourceAlreadyExistsException if the update would result in a duplicate email or license number
     */
    @Override
    @Transactional
    @Caching(evict = {
        @CacheEvict(value = "doctor", key = "#id"),
        @CacheEvict(value = {"doctors", "activeDoctor"}, allEntries = true)
    })
    public DoctorDTO updateDoctor(Long id, DoctorRequest doctorRequest) {
        // Find the doctor to update
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.create("Doctor", "id", id));
        
        // Check for email uniqueness if email is changing
        if (!doctor.getEmail().equals(doctorRequest.getEmail()) &&
                doctorRepository.existsByEmail(doctorRequest.getEmail())) {
            throw ResourceAlreadyExistsException.create("Doctor", "email", doctorRequest.getEmail());
        }
        
        // Check for license number uniqueness if license number is changing
        if (!doctor.getLicenseNumber().equals(doctorRequest.getLicenseNumber()) &&
                doctorRepository.existsByLicenseNumber(doctorRequest.getLicenseNumber())) {
            throw ResourceAlreadyExistsException.create("Doctor", "licenseNumber", doctorRequest.getLicenseNumber());
        }
        
        // Update doctor with request data
        doctorMapper.updateEntityFromRequest(doctor, doctorRequest);
        
        // Save and return
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDTO(updatedDoctor);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @throws ResourceNotFoundException if no doctor is found with the specified ID
     */
    @Override
    @Transactional
    @Caching(evict = {
        @CacheEvict(value = "doctor", key = "#id"),
        @CacheEvict(value = {"doctors", "activeDoctor"}, allEntries = true)
    })
    public void deleteDoctor(Long id) {
        // Check if doctor exists
        if (!doctorRepository.existsById(id)) {
            throw ResourceNotFoundException.create("Doctor", "id", id);
        }
        
        doctorRepository.deleteById(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DoctorDTO> searchDoctorsByName(String name) {
        return doctorRepository.searchByName(name).stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DoctorDTO> getDoctorsBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty).stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "activeDoctor")
    public List<DoctorDTO> getActiveDoctors() {
        return doctorRepository.findByActiveTrue().stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }
} 