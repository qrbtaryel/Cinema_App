package org.example.cinema.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MovieSessionRequest {
    private LocalDateTime date;
    private Long hallId;
    private Long langId;
    private Long sessionId;
}
