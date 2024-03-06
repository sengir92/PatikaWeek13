package dev.patika.vet_management.dto.request.customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    @NotNull(message = "Customer name cannot be empty or null")
    private String name;
    @NotNull(message = "Customer mail cannot be empty or null")
    private String mail;
    @NotNull(message = "Customer phone number cannot be empty or null")
    private String phone;
    @NotNull(message = "Customer address cannot be empty or null")
    private String address;
    @NotNull(message = "Customer city cannot be empty or null")
    private String city;
}
