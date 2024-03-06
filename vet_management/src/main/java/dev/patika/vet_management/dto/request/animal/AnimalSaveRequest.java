package dev.patika.vet_management.dto.request.animal;

import dev.patika.vet_management.entities.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class AnimalSaveRequest {
    @NotNull(message = "Animal name cannot be empty or null")
    private String name;
    @NotNull(message = "Animal species cannot be empty or null")
    private String species;
    @NotNull(message = "Animal breed cannot be empty or null")
    private String breed;
    @NotNull(message = "Animal gender cannot be empty or null")
    private String  gender;
    @NotNull(message = "Animal colour cannot be empty or null")
    private String colour;
    @NotNull(message = "Animal date of birth cannot be empty or null")
    private LocalDate date_birth;
    private Customer customer;
}
