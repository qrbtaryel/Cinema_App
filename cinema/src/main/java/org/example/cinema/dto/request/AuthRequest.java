package org.example.cinema.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {

    @NotBlank(message = "Istifadeci adi bos ola bilmez")
    String username;
    @NotBlank(message = "Sifre bos ola bilmez")
    String password;
}
