package dev.patika.vet_management.api;

import dev.patika.vet_management.business.abstracts.IDoctorService;
import dev.patika.vet_management.business.concretes.DoctorManager;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dto.request.customer.CustomerSaveRequest;
import dev.patika.vet_management.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vet_management.dto.request.doctor.DoctorSaveRequest;
import dev.patika.vet_management.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.vet_management.dto.response.CursorResponse;
import dev.patika.vet_management.dto.response.customer.CustomerResponse;
import dev.patika.vet_management.dto.response.doctor.DoctorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final IDoctorService doctorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> create(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        return this.doctorService.create(doctorSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") long id) {
        return this.doctorService.find(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<DoctorResponse> doctorResponsePage = this.doctorService.cursor(page,pageSize);
        return ResultHelper.cursor(doctorResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        return this.doctorService.delete(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        return this.doctorService.update(doctorUpdateRequest);
    }
}
