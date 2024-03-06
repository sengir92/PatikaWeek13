package dev.patika.vet_management.business.abstracts;

import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.dto.request.customer.CustomerSaveRequest;
import dev.patika.vet_management.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vet_management.dto.response.customer.CustomerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {

    ResultData<CustomerResponse> create(CustomerSaveRequest customerSaveRequest);

    ResultData<CustomerResponse> get(long id);

    Page<CustomerResponse> cursor(int page, int pageSize);

   Result delete(long id);

   ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest);

   ResultData<List<CustomerResponse>> findByName(String name);
}
