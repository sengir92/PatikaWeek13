package dev.patika.vet_management.dto.request.doctor;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DoctorSaveRequest {
    @NotNull(message = "Doctor name cannot be empty or null")
    private String name;
    @NotNull(message = "Doctor mail cannot be empty or null")
    private String mail;
    @NotNull(message = "Doctor phone number cannot be empty or null")
    private String phone;
    @NotNull(message = "Doctor address cannot be empty or null")
    private String address;
    @NotNull(message = "Doctor city cannot be empty or null")
    private String city;
}
