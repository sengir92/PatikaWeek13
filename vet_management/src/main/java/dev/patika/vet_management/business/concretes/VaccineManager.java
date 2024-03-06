package dev.patika.vet_management.business.concretes;

import dev.patika.vet_management.business.abstracts.IVaccineService;
import dev.patika.vet_management.core.config.modelMapper.IModelMapperService;
import dev.patika.vet_management.core.exception.AlreadyRegisterException;
import dev.patika.vet_management.core.exception.NotFoundException;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.Msg;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dao.AnimalRepo;
import dev.patika.vet_management.dao.VaccineRepo;
import dev.patika.vet_management.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vet_management.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vet_management.dto.response.vaccine.VaccineResponse;
import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaccineManager implements IVaccineService {
    private final VaccineRepo vaccineRepo;
    private final IModelMapperService modelMapper;
    private final AnimalRepo animalRepo;

    @Override
    public List<Vaccine> findByNameAndCode(String name, String code) {
        return this.vaccineRepo.findByNameAndCode(name,code);
    }

    @Override
    public ResultData<VaccineResponse> create(VaccineSaveRequest vaccineSaveRequest) {
        List<Vaccine> vaccineList = this.findByNameAndCode(
                vaccineSaveRequest.getName(),
                vaccineSaveRequest.getCode()
        );

        if (!vaccineList.isEmpty() && vaccineList.get(0).getFinishDate().equals(vaccineSaveRequest.getFinishDate())) {
            throw new AlreadyRegisterException(Msg.ALREADY_REGISTERED);
        } else if (!vaccineList.isEmpty() && vaccineList.get(0).getFinishDate().isAfter(LocalDate.now())) {
            throw new  AlreadyRegisterException(Msg.STILL_PROTECT);
        } else {
            Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
            vaccineRepo.save(saveVaccine);
            return ResultHelper.created(this.modelMapper.forResponse().map(saveVaccine, VaccineResponse.class));
        }
    }
    @Override
    public ResultData<VaccineResponse> get(long id) {
        Vaccine vaccine = this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return ResultHelper.successful(this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
    }

    @Override
    public Page<VaccineResponse> cursor(int page, int pageSize) {
        Pageable pegable = PageRequest.of(page, pageSize);
        Page<Vaccine> vaccinePage = this.vaccineRepo.findAll(pegable);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage.map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
        return vaccineResponsePage;
    }

    @Override
    public Result delete(long id) {
        Vaccine vaccine = this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.vaccineRepo.delete(vaccine);
        return ResultHelper.ok();
    }

    @Override
    public ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest) {
        this.get(vaccineUpdateRequest.getId());
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        vaccineRepo.save(updateVaccine);
        return ResultHelper.created(modelMapper.forResponse().map(updateVaccine, VaccineResponse.class));

    }

    @Override
    public ResultData<List<VaccineResponse>> findByAnimalId(Long id) {
        Optional<Animal> animalFromDb = this.animalRepo.findById(id);
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        Animal animal = animalFromDb.get();
        List<Vaccine> vaccineList = this.vaccineRepo.findByAnimal(animal);
        List<VaccineResponse> vaccineResponseList = vaccineList.stream()
                .map(vaccine -> modelMapper.forResponse().map(vaccine,VaccineResponse.class))
                .collect(Collectors.toList());
        if (vaccineResponseList.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        return ResultHelper.successful(vaccineResponseList);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByFinishDateBetween(LocalDate startDate, LocalDate finishDate) {
        List<Vaccine> vaccineList = this.vaccineRepo.findByFinishDateBetween(startDate,finishDate);
        return ResultHelper.successful(vaccineList.stream().map(vaccine -> this.modelMapper.forResponse().map(vaccine,VaccineResponse.class)).collect(Collectors.toList()));
    }
}