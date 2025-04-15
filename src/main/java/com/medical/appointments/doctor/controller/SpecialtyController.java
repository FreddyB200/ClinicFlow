package com.medical.appointments.doctor.controller;

import com.medical.appointments.doctor.dto.SpecialtyDTO;
import com.medical.appointments.doctor.service.SpecialtyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/specialties")
@RequiredArgsConstructor
@Tag(name = "Specialty", description = "Specialty management endpoints")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @GetMapping
    @Operation(summary = "Get all specialties")
    public String getAllSpecialties(Model model) {
        List<SpecialtyDTO> specialties = specialtyService.getAllSpecialties();
        model.addAttribute("specialties", specialties);
        return "specialties/list";
    }

    @GetMapping("/new")
    @Operation(summary = "Show create specialty form")
    public String showCreateForm(Model model) {
        model.addAttribute("specialty", new SpecialtyDTO());
        return "specialties/form";
    }

    @PostMapping
    @Operation(summary = "Create a new specialty")
    public String createSpecialty(@ModelAttribute SpecialtyDTO specialtyDTO) {
        specialtyService.createSpecialty(specialtyDTO);
        return "redirect:/specialties";
    }

    @GetMapping("/edit/{id}")
    @Operation(summary = "Show edit specialty form")
    public String showEditForm(@PathVariable Long id, Model model) {
        SpecialtyDTO specialty = specialtyService.getSpecialtyById(id);
        model.addAttribute("specialty", specialty);
        return "specialties/form";
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update a specialty")
    public String updateSpecialty(@PathVariable Long id, @ModelAttribute SpecialtyDTO specialtyDTO) {
        specialtyService.updateSpecialty(id, specialtyDTO);
        return "redirect:/specialties";
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Delete a specialty")
    public String deleteSpecialty(@PathVariable Long id) {
        specialtyService.deleteSpecialty(id);
        return "redirect:/specialties";
    }

    // REST API endpoints
    @RestController
    @RequestMapping("/api/specialties")
    @Tag(name = "Specialty API", description = "Specialty API endpoints")
    public static class SpecialtyRestController {
        private final SpecialtyService specialtyService;
        public SpecialtyRestController(SpecialtyService specialtyService) { this.specialtyService = specialtyService; }

        @GetMapping
        @Operation(summary = "Get all specialties (API)")
        public List<SpecialtyDTO> getAllSpecialties() {
            return specialtyService.getAllSpecialties();
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get specialty by ID (API)")
        public ResponseEntity<SpecialtyDTO> getSpecialtyById(@PathVariable Long id) {
            SpecialtyDTO specialty = specialtyService.getSpecialtyById(id);
            return specialty != null ? ResponseEntity.ok(specialty) : ResponseEntity.notFound().build();
        }

        @PostMapping
        @Operation(summary = "Create specialty (API)")
        public SpecialtyDTO createSpecialty(@RequestBody SpecialtyDTO specialtyDTO) {
            return specialtyService.createSpecialty(specialtyDTO);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Update specialty (API)")
        public SpecialtyDTO updateSpecialty(@PathVariable Long id, @RequestBody SpecialtyDTO specialtyDTO) {
            return specialtyService.updateSpecialty(id, specialtyDTO);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Delete specialty (API)")
        public void deleteSpecialty(@PathVariable Long id) {
            specialtyService.deleteSpecialty(id);
        }
    }
}
