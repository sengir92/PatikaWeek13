package dev.patika.vet_management.business.concretes;

import dev.patika.vet_management.business.abstracts.IDoctorService;
import dev.patika.vet_management.core.config.modelMapper.IModelMapperService;
import dev.patika.vet_management.core.exception.AlreadyRegisterException;
import dev.patika.vet_management.core.exception.NotFoundException;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.Msg;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dao.DoctorRepo;
import dev.patika.vet_management.dto.request.doctor.DoctorSaveRequest;
import dev.patika.vet_management.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.vet_management.dto.response.doctor.DoctorResponse;
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
public class DoctorManager implements IDoctorService {
    private final DoctorRepo doctorRepo;
    private final IModelMapperService modelMapper;

    public ResultData<DoctorResponse> create(DoctorSaveRequest doctorSaveRequest) {
        Optional<Doctor> doctorFromDb = doctorRepo.findByMail(doctorSaveRequest.getMail());
        if (doctorFromDb.isPresent()) {
            throw new AlreadyRegisterException(Msg.ALREADY_REGISTERED);
        }
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        doctorRepo.save(saveDoctor);
        return ResultHelper.created(modelMapper.forResponse().map(saveDoctor, DoctorResponse.class));

    }

    @Override
    public ResultData<DoctorResponse> find(long id) {
        Doctor doctor = this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return ResultHelper.successful(this.modelMapper.forResponse().map(doctor, DoctorResponse.class));
    }

    @Override
    public Doctor get(long id) {
        Doctor doctor = this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<DoctorResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Doctor> doctorPage = this.doctorRepo.findAll(pageable);
        Page<DoctorResponse> doctorResponsePage = doctorPage
                .map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class));

        return doctorResponsePage;
    }
    @Override
    public Result delete(long id) {
        Doctor doctor = this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.doctorRepo.delete(doctor);
        return ResultHelper.ok();
    }

    @Override
    public ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest) {
        this.get(doctorUpdateRequest.getId());
        Doctor updateDoctor = this.modelMapper.forRequest().map(doctorUpdateRequest, Doctor.class);
        doctorRepo.save(updateDoctor);
        return ResultHelper.created(modelMapper.forResponse().map(updateDoctor, DoctorResponse.class));
    }


}
