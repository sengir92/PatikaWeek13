package dev.patika.vet_management.dao;

import dev.patika.vet_management.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByMail(String mail);
    List<Customer> findByName(String name);


}