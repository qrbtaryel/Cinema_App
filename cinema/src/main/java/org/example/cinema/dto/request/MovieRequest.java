package org.example.cinema.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.cinema.enums.Status;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRequest {
    String name;

    String movieDescription;

    Integer userAge;

    Double imdbRate;

    Integer realiseYear;

    Status status;

    List<Long> genreIds = new ArrayList<>();

    Integer duration;

    String author;

    List<MovieSessionRequest> movieSessionRequests = new ArrayList<>();
}
