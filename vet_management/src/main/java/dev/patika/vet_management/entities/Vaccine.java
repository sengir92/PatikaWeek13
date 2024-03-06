package dev.patika.vet_management.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@RequiredArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id",length = 10,columnDefinition = "serial")
    private long id;

    @Column(name = "vaccine_name",nullable = false)
    private String name;

    @Column(name = "vaccine_code",nullable = false)
    private String code;

    @Column(name = "protection_start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "protection_finish_date",nullable = false)
    private LocalDate finishDate;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
