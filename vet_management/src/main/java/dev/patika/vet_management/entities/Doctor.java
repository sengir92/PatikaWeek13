package dev.patika.vet_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@RequiredArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id", length = 10, columnDefinition = "serial")
    private long id;

    @Column(name = "doctor_name", length = 20, nullable = false)
    private String name;

    @Column(name = "doctor_mail", unique = true, nullable = false)
    @Email(message = "Please enter valid mail address")
    private String mail;

    @Column(name = "doctor_phone",nullable = false)
    private String phone;

    @Column(name = "doctor_address",nullable = false)
    private String address;

    @Column(name = "doctor_city",nullable = false)
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<AvailableDate> availableDates;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Appointment> appointments;
}
