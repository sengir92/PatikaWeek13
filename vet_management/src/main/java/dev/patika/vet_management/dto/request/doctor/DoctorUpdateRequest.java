package dev.patika.vet_management.dto.request.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DoctorUpdateRequest {
    @Positive(message = "ID value must be positive")
    private long id;
    @NotNull(message = "Doctor name cannot be empty or null")
    private String name;
    @Email(message = "Doctor e-mail must comply with the formatted and unique")
    @NotNull(message = "Doctor mail cannot be empty or null")
    private String mail;
    @NotNull(message = "Doctor phone number cannot be empty or null")
    private String phone;
    @NotNull(message = "Doctor address cannot be empty or null")
    private String address;
    @NotNull(message = "Doctor city cannot be empty or null")
    private String city;
}
