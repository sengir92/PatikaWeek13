package dev.patika.vet_management.dao;

import dev.patika.vet_management.dto.response.animal.AnimalResponse;
import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepo extends JpaRepository<Animal,Long> {
    Optional<Animal> findByNameAndCustomerId(String name,long customer_id);
    List<Animal> findByName(String name);
    List<Animal> findByCustomer(Customer customer);

}
