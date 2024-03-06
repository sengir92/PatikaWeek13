package dev.patika.vet_management.api;

import dev.patika.vet_management.business.abstracts.ICustomerService;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dto.request.customer.CustomerSaveRequest;
import dev.patika.vet_management.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vet_management.dto.response.CursorResponse;
import dev.patika.vet_management.dto.response.customer.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> create(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        return this.customerService.create(customerSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") long id) {
        return this.customerService.get(id);
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<CustomerResponse> customerResponsePage = this.customerService.cursor(page,pageSize);
        return ResultHelper.cursor(customerResponsePage);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        return this.customerService.delete(id);
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return this.customerService.update(customerUpdateRequest);
    }

    @GetMapping("/name/{name}")
    public ResultData<List<CustomerResponse>> findByName(@PathVariable("name") String name) {
        return this.customerService.findByName(name);
    }
    
    }
