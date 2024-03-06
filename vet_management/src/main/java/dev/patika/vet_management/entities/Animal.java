package dev.patika.vet_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@RequiredArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id",length = 10,columnDefinition = "serial")
    private long id;

    @Column(name = "animal_name",length = 20,nullable = false)
    private String name;

    @Column(name = "animal_species",nullable = false)
    private String species;

    @Column(name = "animal_breed",nullable = false)
    private String breed;

    @Column(name = "animal_gender",nullable = false)
    private String  gender;

    @Column(name = "animal_colour",nullable = false)
    private String colour;

    @Column(name = "animal_date_birth",nullable = false)
    private LocalDate date_birth;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private List<Vaccine> vaccines;

    @JsonIgnore
    @OneToMany(mappedBy = "animal",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Appointment> appointments;
}
