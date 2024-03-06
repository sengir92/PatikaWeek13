package dev.patika.vet_management.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "available_dates")
@Data
@RequiredArgsConstructor
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_date_id", length = 10, columnDefinition = "serial")
    private long id;

    @Column(name = "available_date", nullable = false)
    private LocalDate availableDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
