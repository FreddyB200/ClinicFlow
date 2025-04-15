package com.medical.appointments.patient.service;

import com.medical.appointments.patient.dto.PatientDTO;
import java.util.List;

public interface PatientService {
    PatientDTO createPatient(PatientDTO patientDTO);
    PatientDTO getPatientById(Long id);
    List<PatientDTO> getAllPatients();
    PatientDTO updatePatient(Long id, PatientDTO patientDTO);
    void deletePatient(Long id);
    List<PatientDTO> searchPatientsByName(String name);
    List<PatientDTO> getActivePatients();
}
