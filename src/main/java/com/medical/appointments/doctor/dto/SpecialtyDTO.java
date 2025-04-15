package com.medical.appointments.doctor.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialtyDTO {
    private Long id;
    private String name;
}
