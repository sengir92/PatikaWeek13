package dev.patika.vet_management.business.concretes;

import dev.patika.vet_management.business.abstracts.IAnimalService;
import dev.patika.vet_management.business.abstracts.IAppointmentService;
import dev.patika.vet_management.business.abstracts.IAvailableDateService;
import dev.patika.vet_management.business.abstracts.IDoctorService;
import dev.patika.vet_management.core.config.modelMapper.IModelMapperService;
import dev.patika.vet_management.core.exception.AlreadyRegisterException;
import dev.patika.vet_management.core.exception.NotFoundException;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.Msg;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dao.AnimalRepo;
import dev.patika.vet_management.dao.AppointmentRepo;
import dev.patika.vet_management.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vet_management.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vet_management.dto.response.appointment.AppointmentResponse;
import dev.patika.vet_management.entities.Animal;
import dev.patika.vet_management.entities.Appointment;
import dev.patika.vet_management.entities.AvailableDate;
import dev.patika.vet_management.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final IModelMapperService modelMapper;
    private final IAnimalService animalService;
    private final IDoctorService doctorService;
    private final IAvailableDateService availableDateService;

    @Override
    public Appointment findByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime appointmentDate) {
        return this.appointmentRepo.findByDoctorAndAppointmentDate(doctor,appointmentDate);
    }

    @Override
    public ResultData<AppointmentResponse> create(AppointmentSaveRequest appointmentSaveRequest) {
        Appointment appointment = this.findByDoctorAndAppointmentDate(
                appointmentSaveRequest.getDoctor(),
                appointmentSaveRequest.getAppointmentDate()
        );

        List<AvailableDate> availableDateList = this.availableDateService.findByDoctorAndAvailableDate(
                appointmentSaveRequest.getDoctor(),
                appointmentSaveRequest.getAppointmentDate().toLocalDate()
        );


        if (appointment != null) {
            throw new AlreadyRegisterException(Msg.HAVE_APPOINTMENT);
        } else if (availableDateList.isEmpty()) {
            throw new AlreadyRegisterException(Msg.NOT_AVAILABE);
        } else {
            Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
            appointmentRepo.save(saveAppointment);
            return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class));
        }

    }
    @Override
    public ResultData<AppointmentResponse> get(long id) {
        Appointment appointment = this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return ResultHelper.successful(this.modelMapper.forResponse().map(appointment,AppointmentResponse.class));
    }

    @Override
    public Page<AppointmentResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<Appointment> appointmentPage = this.appointmentRepo.findAll(pageable);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage.map(appointment -> this.modelMapper.forResponse().map(appointment,AppointmentResponse.class));
        return appointmentResponsePage;
    }

    @Override
    public Result delete(long id) {
        Appointment appointment = this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.appointmentRepo.delete(appointment);
        return ResultHelper.ok();
    }

    @Override
    public ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest) {
        this.get(appointmentUpdateRequest.getId());
        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest,Appointment.class);
        appointmentRepo.save(updateAppointment);
        return ResultHelper.created(modelMapper.forResponse().map(updateAppointment,AppointmentResponse.class));
    }

    @Override
    public ResultData<List<AppointmentResponse>> findByDateBetweenAndAnimal(LocalDateTime startDate, LocalDateTime finishDate, Long id) {
        Animal animal = this.animalService.get(id);
        List<Appointment> appointmentList = this.appointmentRepo.findByAppointmentDateBetweenAndAnimal(startDate,finishDate,animal);
        return ResultHelper.successful(appointmentList.stream().map(appointment -> this.modelMapper.forResponse().map(appointment,AppointmentResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<List<AppointmentResponse>> findByDateBetweenAndDoctor(LocalDateTime startDate, LocalDateTime finishDate, Long id) {
        Doctor doctor = this.doctorService.get(id);
        List<Appointment> appointmentList = this.appointmentRepo.findByAppointmentDateBetweenAndDoctor(startDate,finishDate,doctor);
        return ResultHelper.successful(appointmentList.stream().map(appointment -> this.modelMapper.forResponse().map(appointment,AppointmentResponse.class)).collect(Collectors.toList()));
    }
}
