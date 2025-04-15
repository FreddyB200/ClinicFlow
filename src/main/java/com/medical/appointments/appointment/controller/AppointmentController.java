package com.medical.appointments.appointment.controller;

import com.medical.appointments.appointment.dto.AppointmentDTO;
import com.medical.appointments.appointment.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointment", description = "Appointment management endpoints")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    @Operation(summary = "Get all appointments")
    public String getAllAppointments(Model model) {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments/list";
    }

    @GetMapping("/new")
    @Operation(summary = "Show create appointment form")
    public String showCreateForm(Model model) {
        model.addAttribute("appointment", new AppointmentDTO());
        return "appointments/form";
    }

    @PostMapping
    @Operation(summary = "Create a new appointment")
    public String createAppointment(@ModelAttribute AppointmentDTO appointmentDTO) {
        appointmentService.createAppointment(appointmentDTO);
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    @Operation(summary = "Show edit appointment form")
    public String showEditForm(@PathVariable Long id, Model model) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "appointments/form";
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update an appointment")
    public String updateAppointment(@PathVariable Long id, @ModelAttribute AppointmentDTO appointmentDTO) {
        appointmentService.updateAppointment(id, appointmentDTO);
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Delete an appointment")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }

    // REST API endpoints
    @RestController
    @RequestMapping("/api/appointments")
    @Tag(name = "Appointment API", description = "Appointment API endpoints")
    public static class AppointmentRestController {
        private final AppointmentService appointmentService;
        public AppointmentRestController(AppointmentService appointmentService) { this.appointmentService = appointmentService; }

        @GetMapping
        @Operation(summary = "Get all appointments (API)")
        public List<AppointmentDTO> getAllAppointments() {
            return appointmentService.getAllAppointments();
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get appointment by ID (API)")
        public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
            AppointmentDTO appointment = appointmentService.getAppointmentById(id);
            return appointment != null ? ResponseEntity.ok(appointment) : ResponseEntity.notFound().build();
        }

        @PostMapping
        @Operation(summary = "Create appointment (API)")
        public AppointmentDTO createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
            return appointmentService.createAppointment(appointmentDTO);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Update appointment (API)")
        public AppointmentDTO updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
            return appointmentService.updateAppointment(id, appointmentDTO);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Delete appointment (API)")
        public void deleteAppointment(@PathVariable Long id) {
            appointmentService.deleteAppointment(id);
        }
    }
}
