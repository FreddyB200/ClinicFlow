package com.medical.appointments.appointment.service.impl;

import com.medical.appointments.appointment.dto.AppointmentDTO;
import com.medical.appointments.appointment.model.Appointment;
import com.medical.appointments.appointment.repository.AppointmentRepository;
import com.medical.appointments.appointment.service.AppointmentService;
import com.medical.appointments.doctor.model.Doctor;
import com.medical.appointments.patient.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    private AppointmentDTO toDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        BeanUtils.copyProperties(appointment, dto);
        if (appointment.getDoctor() != null) {
            dto.setDoctorId(appointment.getDoctor().getId());
            dto.setDoctorName(appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName());
        }
        if (appointment.getPatient() != null) {
            dto.setPatientId(appointment.getPatient().getId());
            dto.setPatientName(appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName());
        }
        return dto;
    }

    private Appointment toEntity(AppointmentDTO dto) {
        Appointment entity = new Appointment();
        BeanUtils.copyProperties(dto, entity);
        // Doctor and Patient should be set in controller/service using their repositories
        return entity;
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = toEntity(appointmentDTO);
        return toDTO(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        return appointmentRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        Optional<Appointment> optional = appointmentRepository.findById(id);
        if (optional.isEmpty()) return null;
        Appointment appointment = optional.get();
        BeanUtils.copyProperties(appointmentDTO, appointment, "id");
        return toDTO(appointmentRepository.save(appointment));
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(doctorId, null, null).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByStatus(String status) {
        return appointmentRepository.findByStatus(status).stream().map(this::toDTO).collect(Collectors.toList());
    }
}
