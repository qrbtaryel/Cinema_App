package org.example.cinema.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionRequest {
    private String name;
    private Double surchargeRate;
}
