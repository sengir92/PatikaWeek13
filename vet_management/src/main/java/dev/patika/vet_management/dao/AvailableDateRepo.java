package dev.patika.vet_management.dao;

import dev.patika.vet_management.entities.AvailableDate;

import dev.patika.vet_management.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface AvailableDateRepo extends JpaRepository<AvailableDate,Long> {
  List<AvailableDate> findByDoctorAndAvailableDate(Doctor doctor,LocalDate availableDate);
}
