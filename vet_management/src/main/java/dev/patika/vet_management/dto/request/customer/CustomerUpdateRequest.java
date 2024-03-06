package dev.patika.vet_management.dto.request.customer;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerUpdateRequest {
    @Positive(message = "ID value must be positive")
    private long id;
    @NotNull(message = "Customer name cannot be empty or null")
    private String name;
    @Email(message = "Customer e-mail must comply with the formatted and unique")
    @NotNull(message = "Customer mail cannot be empty or null")
    private String mail;
    @NotNull(message = "Customer phone number cannot be empty or null")
    private String phone;
    @NotNull(message = "Customer address cannot be empty or null")
    private String address;
    @NotNull(message = "Customer city cannot be empty or null")
    private String city;
}
