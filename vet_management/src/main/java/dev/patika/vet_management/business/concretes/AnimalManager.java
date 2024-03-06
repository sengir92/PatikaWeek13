package dev.patika.vet_management.business.concretes;

import dev.patika.vet_management.business.abstracts.IAnimalService;
import dev.patika.vet_management.core.config.modelMapper.IModelMapperService;
import dev.patika.vet_management.core.exception.AlreadyRegisterException;
import dev.patika.vet_management.core.exception.NotFoundException;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.Msg;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dao.AnimalRepo;
import dev.patika.vet_management.dao.CustomerRepo;
import dev.patika.vet_management.dto.request.animal.AnimalSaveRequest;
import dev.patika.vet_management.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vet_management.dto.response.animal.AnimalResponse;
import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalManager implements IAnimalService {
    private final AnimalRepo animalRepo;
    private final IModelMapperService modelMapper;
    private final CustomerRepo customerRepo;

    @Override
    public ResultData<AnimalResponse> create(AnimalSaveRequest animalSaveRequest) {
        Optional<Animal> animalFromDb = animalRepo.findByNameAndCustomerId(animalSaveRequest.getName(), animalSaveRequest.getCustomer().getId());
        if (animalFromDb.isPresent()) {
            throw new AlreadyRegisterException(Msg.ALREADY_REGISTERED);
        }
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        animalRepo.save(saveAnimal);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class));
    }

    @Override
    public ResultData<AnimalResponse> find(long id) {
        Animal animal = this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
         return ResultHelper.successful(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @Override
    public Animal get(long id) {
        Animal animal = this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<AnimalResponse> cursor(int page, int pagesize) {
        Pageable pegable = PageRequest.of(page, pagesize);
        Page<Animal> animalPage = this.animalRepo.findAll(pegable);
        Page<AnimalResponse> animalResponsePage = animalPage.map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
        return animalResponsePage;
    }

    @Override
    public Result delete(long id) {
        Animal animal = this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.animalRepo.delete(animal);
        return ResultHelper.ok();
    }

    @Override
    public ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest) {
        this.get(animalUpdateRequest.getId());
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        animalRepo.save(updateAnimal);
        return ResultHelper.created(modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    @Override
    public ResultData<List<AnimalResponse>> findByName(String name) {
        List<Animal> animalList = this.animalRepo.findByName(name);
        if (animalList.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        List<AnimalResponse> animalResponseList = animalList.stream()
                .map(Animal -> modelMapper.forResponse().map(Animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.successful(animalResponseList);
    }


    @Override
    public ResultData<List<AnimalResponse>> findByCustomerName(String customerName) {
        // Müşteriyi adına göre bul
        List<Customer> customers = this.customerRepo.findByName(customerName);
        if (customers.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }

        // Müşteriye ait hayvanları bul
        List<AnimalResponse> customerAnimalResponseList = new ArrayList<>();
        for (Customer customer : customers) {
            List<Animal> customerAnimalList = this.animalRepo.findByCustomer(customer);
            customerAnimalResponseList.addAll(customerAnimalList.stream()
                    .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class))
                    .collect(Collectors.toList()));
        }

        if (customerAnimalResponseList.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }

        return ResultHelper.successful(customerAnimalResponseList);


    }
}