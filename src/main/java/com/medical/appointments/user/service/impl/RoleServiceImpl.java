package com.medical.appointments.user.service.impl;

import com.medical.appointments.user.dto.RoleDTO;
import com.medical.appointments.user.model.Role;
import com.medical.appointments.user.repository.RoleRepository;
import com.medical.appointments.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    private RoleDTO toDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        BeanUtils.copyProperties(role, dto);
        return dto;
    }

    private Role toEntity(RoleDTO dto) {
        Role entity = new Role();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = toEntity(roleDTO);
        return toDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        return roleRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isEmpty()) return null;
        Role role = optional.get();
        BeanUtils.copyProperties(roleDTO, role, "id");
        return toDTO(roleRepository.save(role));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDTO getRoleByName(String name) {
        return roleRepository.findByName(name).map(this::toDTO).orElse(null);
    }
}
