package dev.patika.vet_management.business.abstracts;

import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.dto.request.doctor.DoctorSaveRequest;
import dev.patika.vet_management.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.vet_management.dto.response.doctor.DoctorResponse;
import dev.patika.vet_management.entities.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IDoctorService {
    ResultData<DoctorResponse> create(DoctorSaveRequest doctorSaveRequest);

    ResultData<DoctorResponse> find(long id);

    Doctor get(long id);

    Page<DoctorResponse> cursor(int page, int pageSize);

    Result delete(long id);

    ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest);


}
