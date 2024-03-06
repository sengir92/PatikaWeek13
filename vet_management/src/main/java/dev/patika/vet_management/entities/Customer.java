package dev.patika.vet_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", length = 10, columnDefinition = "serial")
    private long id;

    @Column(name = "customer_name", length = 20, nullable = false)
    private String name;

    @Column(name = "customer_mail", unique = true, nullable = false)
    @Email(message = "Please enter valid mail address")
    private String mail;

    @Column(name = "customer_phone",nullable = false)
    private String phone;

    @Column(name = "customer_address",nullable = false)
    private String address;

    @Column(name = "customer_city",nullable = false)
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Animal> animals;
}
