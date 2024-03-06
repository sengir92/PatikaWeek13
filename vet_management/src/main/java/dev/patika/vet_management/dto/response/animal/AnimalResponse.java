package dev.patika.vet_management.dto.response.animal;

import dev.patika.vet_management.entities.Appointment;
import dev.patika.vet_management.entities.Customer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class AnimalResponse {
    private long id;
    private String name;
    private String species;
    private String breed;
    private String  gender;
    private String colour;
    private LocalDate date_birth;
    private Customer customer;
}
