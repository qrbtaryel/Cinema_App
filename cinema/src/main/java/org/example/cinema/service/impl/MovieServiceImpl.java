package org.example.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.request.MovieRequest;
import org.example.cinema.dto.request.MovieSessionRequest;
import org.example.cinema.dto.response.MovieResponse;
import org.example.cinema.dto.response.SessionResponse;
import org.example.cinema.mapper.MovieMapper;
import org.example.cinema.model.*;
import org.example.cinema.repository.MovieRepository;
import org.example.cinema.repository.MovieSessionRepository;
import org.example.cinema.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieSessionRepository movieSessionRepository;
    private final MovieMapper movieMapper;
    private final SessionService sessionService;
    private final LanguageService languageService;
    private final HallService hallService;
    private final GenreService genreService;

    @Override
    @Transactional
    public Page<MovieResponse> getMovies(String name, String movieDescription, Integer realiseYear, Double imdbRate, Pageable pageable) {
        return movieRepository.findAll(name, movieDescription, imdbRate, realiseYear, pageable).map(movieMapper::toResponse);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film tapılmadı"));
    }

    @Override
    public MovieSession findMovieSessionById(Long id) {
        return movieSessionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film Sessiyası tapılmadı"));
    }

    @Override
    public MovieResponse findMovieById(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film tapılmadı"));
    }

    @Override
    @Transactional
    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = movieMapper.toEntity(request);
        List<Genre> genres = genreService.getGenres(request.getGenreIds());
        movie.setGenres(genres);
        Movie newMovie = movieRepository.save(movie);

        saveMovieSessions(newMovie, request.getMovieSessionRequests());

        return movieMapper.toResponse(newMovie);
    }

    private void saveMovieSessions(Movie movie, List<MovieSessionRequest> requests) {
        movieSessionRepository.deleteAllByMovieId(movie.getId());

        List<MovieSession> movieSessions = requests.stream().map(movieSessionRequest -> {
            Session session = sessionService.findById(movieSessionRequest.getSessionId());
            Language language = languageService.findById(movieSessionRequest.getLangId());
            Hall hall = hallService.findById(movieSessionRequest.getHallId());

            return MovieSession.builder()
                    .movie(movie)
                    .session(session)
                    .language(language)
                    .hall(hall)
                    .date(movieSessionRequest.getDate())
                    .build();
        }).collect(Collectors.toList());

        List<MovieSession> newMovieSessions = movieSessionRepository.saveAll(movieSessions);
        movie.setSessions(newMovieSessions);
    }

    @Override
    @Transactional
    public void updateMovie(Long id, MovieRequest movieRequest) {
        Movie movie = findById(id);
        List<Genre> genres = genreService.getGenres(movieRequest.getGenreIds());
        movieMapper.update(movie, movieRequest);
        movie.setGenres(genres);
        saveMovieSessions(movie, movieRequest.getMovieSessionRequests());
    }

    @Override
    @Transactional
    public List<SessionResponse> getMovieSessions(Long id) {
        Movie movie = findById(id);
        return movie.getSessions().stream()
                .map(movieSession -> SessionResponse.builder()
                        .id(movieSession.getSession().getId())
                        .name(movieSession.getSession().getName())
                        .build())
                .toList();
    }
}
