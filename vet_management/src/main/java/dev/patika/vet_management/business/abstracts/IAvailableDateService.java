package dev.patika.vet_management.business.abstracts;

import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.vet_management.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.vet_management.dto.response.availableDate.AvailableDateResponse;
import dev.patika.vet_management.entities.AvailableDate;
import dev.patika.vet_management.entities.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IAvailableDateService {
    ResultData<AvailableDateResponse> create(AvailableDateSaveRequest availableDateSaveRequest);

    ResultData<AvailableDateResponse> get(long id);

    Page<AvailableDateResponse> cursor(int page, int pageSize);

    Result delete(long id);

    ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest);

    List<AvailableDate> findByDoctorAndAvailableDate(Doctor doctor, LocalDate availableDate);
}
