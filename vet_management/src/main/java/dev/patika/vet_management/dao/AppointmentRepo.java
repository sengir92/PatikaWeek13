package dev.patika.vet_management.dao;

import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Appointment;
import dev.patika.vet_management.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    Appointment findByDoctorAndAppointmentDate(Doctor doctor,LocalDateTime appointmentDate);

    List<Appointment> findByAppointmentDateBetweenAndAnimal(LocalDateTime startDate,LocalDateTime finishDate,Animal animal);

    List<Appointment> findByAppointmentDateBetweenAndDoctor(LocalDateTime startDate,LocalDateTime finishDate,Doctor doctor);
}
