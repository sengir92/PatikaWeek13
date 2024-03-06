package dev.patika.vet_management.dao;

import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface VaccineRepo extends JpaRepository<Vaccine,Long> {
    List<Vaccine> findByAnimal(Animal animal);
    List<Vaccine> findByNameAndCode(String name,String code);
    List<Vaccine> findByFinishDateBetween(LocalDate startDate,LocalDate endDate);
}
