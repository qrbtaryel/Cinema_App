package org.example.cinema.service;

import org.example.cinema.dto.request.GenreRequest;
import org.example.cinema.dto.response.GenreResponse;
import org.example.cinema.model.Genre;

import java.util.List;

public interface GenreService {
    List<GenreResponse> getGenres();
    List<Genre> getGenres(List<Long> ids);

    List<GenreResponse> saveAll(List<GenreRequest> genres);
}
