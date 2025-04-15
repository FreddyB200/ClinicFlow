package com.medical.appointments.patient.service.impl;

import com.medical.appointments.patient.dto.PatientDTO;
import com.medical.appointments.patient.model.Patient;
import com.medical.appointments.patient.repository.PatientRepository;
import com.medical.appointments.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    private PatientDTO toDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        BeanUtils.copyProperties(patient, dto);
        return dto;
    }

    private Patient toEntity(PatientDTO dto) {
        Patient entity = new Patient();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = toEntity(patientDTO);
        patient.setActive(true);
        return toDTO(patientRepository.save(patient));
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        return patientRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Optional<Patient> optional = patientRepository.findById(id);
        if (optional.isEmpty()) return null;
        Patient patient = optional.get();
        BeanUtils.copyProperties(patientDTO, patient, "id");
        return toDTO(patientRepository.save(patient));
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<PatientDTO> searchPatientsByName(String name) {
        return patientRepository.findAll().stream()
                .filter(p -> (p.getFirstName() + " " + p.getLastName()).toLowerCase().contains(name.toLowerCase()))
                .map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<PatientDTO> getActivePatients() {
        return patientRepository.findByActiveTrue().stream().map(this::toDTO).collect(Collectors.toList());
    }
}
