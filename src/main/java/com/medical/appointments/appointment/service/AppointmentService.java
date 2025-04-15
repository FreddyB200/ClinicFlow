package com.medical.appointments.appointment.service;

import com.medical.appointments.appointment.dto.AppointmentDTO;
import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO getAppointmentById(Long id);
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO);
    void deleteAppointment(Long id);
    List<AppointmentDTO> getAppointmentsByDoctor(Long doctorId);
    List<AppointmentDTO> getAppointmentsByPatient(Long patientId);
    List<AppointmentDTO> getAppointmentsByStatus(String status);
}
