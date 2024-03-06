package dev.patika.vet_management.business.concretes;

import dev.patika.vet_management.business.abstracts.IAvailableDateService;
import dev.patika.vet_management.core.config.modelMapper.IModelMapperService;
import dev.patika.vet_management.core.exception.AlreadyRegisterException;
import dev.patika.vet_management.core.exception.NotFoundException;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.Msg;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dao.AvailableDateRepo;
import dev.patika.vet_management.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.vet_management.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.vet_management.dto.response.availableDate.AvailableDateResponse;
import dev.patika.vet_management.entities.AvailableDate;
import dev.patika.vet_management.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements IAvailableDateService {
    private final AvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapper;

    @Override
    public List<AvailableDate> findByDoctorAndAvailableDate(Doctor doctor, LocalDate availableDate) {
        return this.availableDateRepo.findByDoctorAndAvailableDate(doctor,availableDate);
    }

    @Override
    public ResultData<AvailableDateResponse> create(AvailableDateSaveRequest availableDateSaveRequest) {
        List<AvailableDate> availableDateList = this.findByDoctorAndAvailableDate(
                availableDateSaveRequest.getDoctor(),
                availableDateSaveRequest.getAvailableDate()
        );
        if (!availableDateList.isEmpty()) {
            throw new AlreadyRegisterException(Msg.ALREADY_REGISTERED);
        } else {

            AvailableDate saveAvailabledate = this.modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
            availableDateRepo.save(saveAvailabledate);
            return ResultHelper.created(modelMapper.forResponse().map(saveAvailabledate, AvailableDateResponse.class));
        }
    }

    @Override
    public ResultData<AvailableDateResponse> get(long id) {
        AvailableDate availableDate = this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return ResultHelper.successful(this.modelMapper.forResponse().map(availableDate,AvailableDateResponse.class));
    }

    @Override
    public Page<AvailableDateResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<AvailableDate> availableDatePage = this.availableDateRepo.findAll(pageable);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage
                .map(availableDate -> this.modelMapper.forResponse().map(availableDate,AvailableDateResponse.class));
        return availableDateResponsePage;
    }

    @Override
    public Result delete(long id) {
        AvailableDate availableDate = this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.availableDateRepo.delete(availableDate);
        return null;
    }

    @Override
    public ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        this.get(availableDateUpdateRequest.getId());
        AvailableDate updateAvailableDate  = this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        availableDateRepo.save(updateAvailableDate);
        return ResultHelper.created(modelMapper.forResponse().map(updateAvailableDate,AvailableDateResponse.class));
    }
}
