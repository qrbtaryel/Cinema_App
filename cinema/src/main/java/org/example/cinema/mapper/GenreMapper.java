package org.example.cinema.mapper;

import org.example.cinema.dto.request.GenreRequest;
import org.example.cinema.dto.response.GenreResponse;
import org.example.cinema.model.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre toGenre(GenreRequest genreRequest);
    GenreResponse toGenreResponse(Genre genre);
}
