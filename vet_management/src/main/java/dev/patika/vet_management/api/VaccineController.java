package dev.patika.vet_management.api;

import dev.patika.vet_management.business.abstracts.IVaccineService;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vet_management.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vet_management.dto.response.CursorResponse;
import dev.patika.vet_management.dto.response.vaccine.VaccineResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {
    private final IVaccineService vaccineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> create(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        return this.vaccineService.create(vaccineSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") long id) {return this.vaccineService.get(id);}

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<VaccineResponse> vaccineResponsePage = this.vaccineService.cursor(page,pageSize);
        return ResultHelper.cursor(vaccineResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {return this.vaccineService.delete(id);}

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        return this.vaccineService.update(vaccineUpdateRequest);
    }

    @GetMapping("/animal/{id}")
    public ResultData<List<VaccineResponse>> findByAnimalId(@PathVariable("id") long id) {
        return this.vaccineService.findByAnimalId(id);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> filterByDate(
            @RequestParam(name = "startDate") LocalDate startDate,
            @RequestParam(name = "finishDate") LocalDate finishDate
            ) {
        return this.vaccineService.findByFinishDateBetween(startDate,finishDate);
    }

}
