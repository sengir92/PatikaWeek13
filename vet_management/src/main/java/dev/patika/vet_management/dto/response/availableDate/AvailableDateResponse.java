package dev.patika.vet_management.dto.response.availableDate;

import dev.patika.vet_management.entities.Doctor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class AvailableDateResponse {
    private long id;
    private LocalDate availableDate;
    private Doctor doctor;

 }
