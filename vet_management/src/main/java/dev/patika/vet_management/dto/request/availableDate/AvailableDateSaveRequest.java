package dev.patika.vet_management.dto.request.availableDate;

import dev.patika.vet_management.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class AvailableDateSaveRequest {
    @NotNull(message = "Available date cannot be empty or null")
    private LocalDate availableDate;
    private Doctor doctor;

}
