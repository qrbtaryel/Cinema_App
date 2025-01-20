package org.example.cinema.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {
    @NotEmpty(message = "ad boş ola bilməz")
    private String name;

    @NotEmpty(message = "soyad boş ola bilməz")
    private String surname;

    @NotEmpty(message = "ata adi boş ola bilməz")
    private String patronymic;

    @NotEmpty(message = "Istifadeci adi boş ola bilməz")
    private String username;

    @NotEmpty(message = "passwordu boş ola bilməz")
    private String password;

    @NotEmpty(message = "dogum tarixi boş ola bilməz")
    private LocalDate birthdate;
}
