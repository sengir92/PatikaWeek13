package dev.patika.vet_management.dto.request.appointment;

import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AppointmentSaveRequest {
    @NotNull(message = "Appointment time cannot be empty or null")
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;
}
