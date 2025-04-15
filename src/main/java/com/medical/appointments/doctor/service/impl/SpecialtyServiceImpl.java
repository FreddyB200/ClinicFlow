package com.medical.appointments.doctor.service.impl;

import com.medical.appointments.doctor.dto.SpecialtyDTO;
import com.medical.appointments.doctor.model.Specialty;
import com.medical.appointments.doctor.repository.SpecialtyRepository;
import com.medical.appointments.doctor.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    private SpecialtyDTO toDTO(Specialty specialty) {
        SpecialtyDTO dto = new SpecialtyDTO();
        BeanUtils.copyProperties(specialty, dto);
        return dto;
    }

    private Specialty toEntity(SpecialtyDTO dto) {
        Specialty entity = new Specialty();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public SpecialtyDTO createSpecialty(SpecialtyDTO specialtyDTO) {
        Specialty specialty = toEntity(specialtyDTO);
        return toDTO(specialtyRepository.save(specialty));
    }

    @Override
    public SpecialtyDTO getSpecialtyById(Long id) {
        return specialtyRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<SpecialtyDTO> getAllSpecialties() {
        return specialtyRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public SpecialtyDTO updateSpecialty(Long id, SpecialtyDTO specialtyDTO) {
        Optional<Specialty> optional = specialtyRepository.findById(id);
        if (optional.isEmpty()) return null;
        Specialty specialty = optional.get();
        BeanUtils.copyProperties(specialtyDTO, specialty, "id");
        return toDTO(specialtyRepository.save(specialty));
    }

    @Override
    public void deleteSpecialty(Long id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public SpecialtyDTO getSpecialtyByName(String name) {
        return specialtyRepository.findByName(name).map(this::toDTO).orElse(null);
    }
}
