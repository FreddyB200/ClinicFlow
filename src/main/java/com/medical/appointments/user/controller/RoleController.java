package com.medical.appointments.user.controller;

import com.medical.appointments.user.dto.RoleDTO;
import com.medical.appointments.user.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = "Role", description = "Role management endpoints")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    @Operation(summary = "Get all roles")
    public String getAllRoles(Model model) {
        List<RoleDTO> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "roles/list";
    }

    @GetMapping("/new")
    @Operation(summary = "Show create role form")
    public String showCreateForm(Model model) {
        model.addAttribute("role", new RoleDTO());
        return "roles/form";
    }

    @PostMapping
    @Operation(summary = "Create a new role")
    public String createRole(@ModelAttribute RoleDTO roleDTO) {
        roleService.createRole(roleDTO);
        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    @Operation(summary = "Show edit role form")
    public String showEditForm(@PathVariable Long id, Model model) {
        RoleDTO role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "roles/form";
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update a role")
    public String updateRole(@PathVariable Long id, @ModelAttribute RoleDTO roleDTO) {
        roleService.updateRole(id, roleDTO);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Delete a role")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return "redirect:/roles";
    }

    // REST API endpoints
    @RestController
    @RequestMapping("/api/roles")
    @Tag(name = "Role API", description = "Role API endpoints")
    public static class RoleRestController {
        private final RoleService roleService;
        public RoleRestController(RoleService roleService) { this.roleService = roleService; }

        @GetMapping
        @Operation(summary = "Get all roles (API)")
        public List<RoleDTO> getAllRoles() {
            return roleService.getAllRoles();
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get role by ID (API)")
        public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
            RoleDTO role = roleService.getRoleById(id);
            return role != null ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
        }

        @PostMapping
        @Operation(summary = "Create role (API)")
        public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
            return roleService.createRole(roleDTO);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Update role (API)")
        public RoleDTO updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
            return roleService.updateRole(id, roleDTO);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Delete role (API)")
        public void deleteRole(@PathVariable Long id) {
            roleService.deleteRole(id);
        }
    }
}
