package com.medical.appointments.user.service;

import com.medical.appointments.user.dto.RoleDTO;
import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO getRoleById(Long id);
    List<RoleDTO> getAllRoles();
    RoleDTO updateRole(Long id, RoleDTO roleDTO);
    void deleteRole(Long id);
    RoleDTO getRoleByName(String name);
}
