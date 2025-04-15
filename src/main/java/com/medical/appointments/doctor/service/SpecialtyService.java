package com.medical.appointments.doctor.service;

import com.medical.appointments.doctor.dto.SpecialtyDTO;
import java.util.List;

public interface SpecialtyService {
    SpecialtyDTO createSpecialty(SpecialtyDTO specialtyDTO);
    SpecialtyDTO getSpecialtyById(Long id);
    List<SpecialtyDTO> getAllSpecialties();
    SpecialtyDTO updateSpecialty(Long id, SpecialtyDTO specialtyDTO);
    void deleteSpecialty(Long id);
    SpecialtyDTO getSpecialtyByName(String name);
}
