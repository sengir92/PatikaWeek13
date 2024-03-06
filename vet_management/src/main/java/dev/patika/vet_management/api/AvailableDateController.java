package dev.patika.vet_management.api;

import dev.patika.vet_management.business.abstracts.IAvailableDateService;
import dev.patika.vet_management.business.concretes.AvailableDateManager;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.vet_management.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.vet_management.dto.response.CursorResponse;
import dev.patika.vet_management.dto.response.availableDate.AvailableDateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/availabledates")
@RequiredArgsConstructor
public class AvailableDateController {
    private final AvailableDateManager availableDateManager;
    private final IAvailableDateService availableDateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> create(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        return this.availableDateService.create(availableDateSaveRequest);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") long id) {
        return this.availableDateService.get(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<AvailableDateResponse> availableDateResponsePage = this.availableDateService.cursor(page,pageSize);
        return ResultHelper.cursor(availableDateResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {return  this.availableDateService.delete(id);}

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        return this.availableDateService.update(availableDateUpdateRequest);
    }

}
