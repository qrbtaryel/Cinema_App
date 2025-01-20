package org.example.cinema.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.cinema.enums.Status;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    Long id;

    String name;

    String movieDescription;

    Integer userAge;

    Double imdbRate;

    Integer realiseYear;

    Status status;

    List<GenreResponse> genres = new ArrayList<>();

    String author;

    Integer duration;

    List<MovieSessionResponse> movieSessions = new ArrayList<>();
}
