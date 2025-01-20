package org.example.cinema.mapper;

import org.example.cinema.dto.request.MovieRequest;
import org.example.cinema.dto.response.MovieResponse;
import org.example.cinema.dto.response.MovieSessionResponse;
import org.example.cinema.model.Movie;
import org.example.cinema.model.MovieSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    Movie toEntity(MovieRequest request);

    MovieSessionResponse toMovieSessionResponse(MovieSession movieSession);

    @Mapping(target = "movieSessions", source = "sessions")
    MovieResponse toResponse(Movie movie);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    void update(@MappingTarget Movie movie, MovieRequest movieRequest);
}
