package dev.patika.vet_management.dto.response.doctor;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DoctorResponse {
    private long id;
    private String name;
    private String mail;
    private String phone;
    private String address;
    private String city;
}
