package dev.patika.vet_management.api;

import dev.patika.vet_management.business.abstracts.IAppointmentService;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vet_management.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vet_management.dto.response.CursorResponse;
import dev.patika.vet_management.dto.response.appointment.AppointmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final IAppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> create(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return this.appointmentService.create(appointmentSaveRequest);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") long id) {return this.appointmentService.get(id);}

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<AppointmentResponse> appointmentResponsePage = this.appointmentService.cursor(page,pageSize);
        return ResultHelper.cursor(appointmentResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {return this.appointmentService.delete(id);}

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        return this.appointmentService.update(appointmentUpdateRequest);
    }

    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getAnimalAppointments(
            @PathVariable("id") Long id,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate
            ) {
        return this.appointmentService.findByDateBetweenAndAnimal(startDate,finishDate,id);
    }

    @GetMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getDoctorAppointments(
            @PathVariable("id") Long id,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate
    ) {
        return this.appointmentService.findByDateBetweenAndDoctor(startDate,finishDate,id);
    }
}
