package org.example.cinema.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
    private Long id;
    private BigDecimal price;
    private LocalDateTime soldTime;
    private Boolean sold;
    private String movieName;
    private String sessionName;
    private UserResponse user;
    private Integer seatNumber;
    private String hallName;
}
