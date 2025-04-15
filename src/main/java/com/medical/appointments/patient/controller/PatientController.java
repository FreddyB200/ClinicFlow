package com.medical.appointments.patient.controller;

import com.medical.appointments.patient.dto.PatientDTO;
import com.medical.appointments.patient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
@Tag(name = "Patient", description = "Patient management endpoints")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Get all patients")
    public String getAllPatients(Model model) {
        List<PatientDTO> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients/list";
    }

    @GetMapping("/new")
    @Operation(summary = "Show create patient form")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new PatientDTO());
        return "patients/form";
    }

    @PostMapping
    @Operation(summary = "Create a new patient")
    public String createPatient(@ModelAttribute PatientDTO patientDTO) {
        patientService.createPatient(patientDTO);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    @Operation(summary = "Show edit patient form")
    public String showEditForm(@PathVariable Long id, Model model) {
        PatientDTO patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patients/form";
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update a patient")
    public String updatePatient(@PathVariable Long id, @ModelAttribute PatientDTO patientDTO) {
        patientService.updatePatient(id, patientDTO);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Delete a patient")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    // REST API endpoints
    @RestController
    @RequestMapping("/api/patients")
    @Tag(name = "Patient API", description = "Patient API endpoints")
    public static class PatientRestController {
        private final PatientService patientService;
        public PatientRestController(PatientService patientService) { this.patientService = patientService; }

        @GetMapping
        @Operation(summary = "Get all patients (API)")
        public List<PatientDTO> getAllPatients() {
            return patientService.getAllPatients();
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get patient by ID (API)")
        public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
            PatientDTO patient = patientService.getPatientById(id);
            return patient != null ? ResponseEntity.ok(patient) : ResponseEntity.notFound().build();
        }

        @PostMapping
        @Operation(summary = "Create patient (API)")
        public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) {
            return patientService.createPatient(patientDTO);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Update patient (API)")
        public PatientDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
            return patientService.updatePatient(id, patientDTO);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Delete patient (API)")
        public void deletePatient(@PathVariable Long id) {
            patientService.deletePatient(id);
        }
    }
}
