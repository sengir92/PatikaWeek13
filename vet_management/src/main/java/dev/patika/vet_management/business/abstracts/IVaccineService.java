package dev.patika.vet_management.business.abstracts;

import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vet_management.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vet_management.dto.response.vaccine.VaccineResponse;
import dev.patika.vet_management.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IVaccineService {
    ResultData<VaccineResponse> create(VaccineSaveRequest vaccineSaveRequest);

    ResultData<VaccineResponse> get(long id);

    Page<VaccineResponse> cursor(int page, int pageSize);

    Result delete(long id);

    ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest);

    ResultData<List<VaccineResponse>> findByAnimalId(Long id);

    List<Vaccine> findByNameAndCode(String name,String code);

   ResultData<List<VaccineResponse>> findByFinishDateBetween(LocalDate startDate,LocalDate finishDate);
}
