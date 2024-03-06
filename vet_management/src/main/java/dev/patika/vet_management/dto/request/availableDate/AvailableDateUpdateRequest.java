package dev.patika.vet_management.dto.request.availableDate;

import dev.patika.vet_management.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class AvailableDateUpdateRequest {
    @Positive(message = "ID value must be positive")
    private long id;
    @NotNull(message = "Available date cannot be empty or null")
    private LocalDate availableDate;
    private Doctor doctor;
}
