package dev.patika.vet_management.business.abstracts;

import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.dto.request.animal.AnimalSaveRequest;
import dev.patika.vet_management.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vet_management.dto.response.animal.AnimalResponse;
import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAnimalService {

    ResultData<AnimalResponse> create(AnimalSaveRequest animalSaveRequest);

    ResultData<AnimalResponse> find(long id);

    Animal get(long id);

    Page<AnimalResponse> cursor(int page,int pagesize);

    Result delete(long id);

    ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest);

    ResultData<List<AnimalResponse>> findByName(String name);

    ResultData<List<AnimalResponse>> findByCustomerName(String customerName);


}
