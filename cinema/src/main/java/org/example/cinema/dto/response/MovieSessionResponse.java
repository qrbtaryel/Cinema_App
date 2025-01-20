package org.example.cinema.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MovieSessionResponse {
    private Long id;
    private LocalDateTime date;
    private HallResponse hall;
    private SessionResponse session;
    private LanguageResponse language;
}
