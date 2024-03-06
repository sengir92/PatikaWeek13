package dev.patika.vet_management.business.abstracts;

import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vet_management.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vet_management.dto.response.appointment.AppointmentResponse;
import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Appointment;
import dev.patika.vet_management.entities.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    ResultData<AppointmentResponse> create(AppointmentSaveRequest appointmentSaveRequest);

    ResultData<AppointmentResponse> get(long id);


    Page<AppointmentResponse> cursor(int page, int pageSize);

    Result delete(long id);

    ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest);

    Appointment findByDoctorAndAppointmentDate(Doctor doctor,LocalDateTime appointmentDate);

    ResultData<List<AppointmentResponse>> findByDateBetweenAndAnimal(LocalDateTime startDate, LocalDateTime finishDate, Long id);

    ResultData<List<AppointmentResponse>> findByDateBetweenAndDoctor(LocalDateTime startDate, LocalDateTime finishDate, Long id);

}
