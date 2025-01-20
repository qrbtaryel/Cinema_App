package org.example.cinema.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionResponse {
    private Long id;
    private String name;
    private Double surchargeRate;
}
