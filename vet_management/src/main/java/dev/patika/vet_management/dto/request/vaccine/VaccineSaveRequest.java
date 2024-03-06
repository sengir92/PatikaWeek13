package dev.patika.vet_management.dto.request.vaccine;

import dev.patika.vet_management.entities.Animal;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class VaccineSaveRequest {
    @NotNull(message = "Vaccine name cannot be empty or null")
    private String name;
    @NotNull(message = "Vaccine code cannot be empty or null")
    private String code;
    @NotNull(message = "Protection start date cannot be empty or null")
    private LocalDate startDate;
    @NotNull(message = "Protection finish date cannot be empty or null")
    private LocalDate finishDate;
    private Animal animal;
}
