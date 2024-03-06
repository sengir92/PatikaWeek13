package dev.patika.vet_management.dto.response.appointment;

import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Doctor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AppointmentResponse {
    private long id;
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;
}
