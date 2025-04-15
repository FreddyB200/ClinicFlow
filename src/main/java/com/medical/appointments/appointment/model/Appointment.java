package com.medical.appointments.appointment.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.medical.appointments.doctor.model.Doctor;
import com.medical.appointments.patient.model.Patient;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @Column(nullable = false)
    private String status; // SCHEDULED, COMPLETED, CANCELLED

    private String notes;
}
