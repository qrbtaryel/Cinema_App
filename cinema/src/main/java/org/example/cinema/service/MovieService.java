package org.example.cinema.service;

import org.example.cinema.dto.request.MovieRequest;
import org.example.cinema.dto.response.MovieResponse;
import org.example.cinema.dto.response.SessionResponse;
import org.example.cinema.model.Movie;
import org.example.cinema.model.MovieSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService { 
    Page<MovieResponse> getMovies(String name, String movieDescription, Integer realiseYear, Double imdbRate, Pageable pageable);

    Movie findById(Long id);
    MovieSession findMovieSessionById(Long id);

    MovieResponse findMovieById(Long id);

    MovieResponse createMovie(MovieRequest request);

    void updateMovie(Long id, MovieRequest movieRequest);

    List<SessionResponse> getMovieSessions(Long id);
}
