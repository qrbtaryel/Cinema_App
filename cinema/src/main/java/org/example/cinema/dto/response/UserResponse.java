package org.example.cinema.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String username;
    private BigDecimal balance;
}
