package dev.patika.vet_management.business.concretes;

import dev.patika.vet_management.business.abstracts.ICustomerService;
import dev.patika.vet_management.core.config.modelMapper.IModelMapperService;
import dev.patika.vet_management.core.exception.AlreadyRegisterException;
import dev.patika.vet_management.core.exception.NotFoundException;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.Msg;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dao.CustomerRepo;
import dev.patika.vet_management.dto.request.customer.CustomerSaveRequest;
import dev.patika.vet_management.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vet_management.dto.response.customer.CustomerResponse;
import dev.patika.vet_management.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    @Override
    public ResultData<CustomerResponse> create(CustomerSaveRequest customerSaveRequest) {
        Optional<Customer> customerFromDb = customerRepo.findByMail(customerSaveRequest.getMail());
        if (customerFromDb.isPresent()) {
            throw new AlreadyRegisterException(Msg.ALREADY_REGISTERED);
        }
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        customerRepo.save(saveCustomer);
        return ResultHelper.created(modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));

    }

    @Override
    public ResultData<CustomerResponse> get(long id) {
        Customer customer = this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        return ResultHelper.successful(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @Override
    public Page<CustomerResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customerPage = this.customerRepo.findAll(pageable);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));

        return customerResponsePage;
    }
    @Override
    public Result delete(long id) {
        Customer customer = this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.customerRepo.delete(customer);
        return ResultHelper.ok();
    }

    @Override
    public ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest) {
        this.get(customerUpdateRequest.getId());
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        customerRepo.save(updateCustomer);
        return ResultHelper.created(modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));
    }

    @Override
    public ResultData<List<CustomerResponse>> findByName(String name) {
        List<Customer> customerList = this.customerRepo.findByName(name);
        if (customerList.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        List<CustomerResponse> customerResponseList = customerList.stream()
                .map(Customer -> modelMapper.forResponse().map(Customer, CustomerResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.successful(customerResponseList);
    }
}