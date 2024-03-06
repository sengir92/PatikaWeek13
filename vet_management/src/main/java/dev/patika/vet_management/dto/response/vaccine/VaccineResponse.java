package dev.patika.vet_management.dto.response.vaccine;

import dev.patika.vet_management.entities.Animal;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class VaccineResponse {
    private long id;
    private String name;
    private String code;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Animal animal;
}
