package dev.patika.vet_management.dto.request.appointment;

import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AppointmentUpdateRequest {
    @Positive(message = "ID value must be positive")
    private long id;
    @NotNull(message = "Appointment time cannot be empty or null")
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;
}
