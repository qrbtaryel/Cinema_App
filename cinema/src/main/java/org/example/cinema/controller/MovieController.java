package org.example.cinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.request.MovieRequest;
import org.example.cinema.dto.response.MovieResponse;
import org.example.cinema.dto.response.SessionResponse;
import org.example.cinema.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Movie Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<MovieResponse>> getMovies(Pageable pageable,
                                                         @RequestParam(required = false, defaultValue = "") String name,
                                                         @RequestParam(required = false, defaultValue = "") String movieDescription,
                                                         @RequestParam(required = false) Integer realiseYear,
                                                         @RequestParam(required = false) Double imdbRate) {

        return ResponseEntity.ok(movieService.getMovies(name, movieDescription, realiseYear, imdbRate, pageable));
    }

    @GetMapping("/sessions/{id}")
    public ResponseEntity<List<SessionResponse>> getMovieSessions(@PathVariable Long id) {

        return ResponseEntity.ok(movieService.getMovieSessions(id));
    }


    @Operation(summary = "Film əlavə etmək ucun POST API")
    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.createMovie(movieRequest));
    }

    @Operation(summary = "Film-i yeniləmək ucun POST API")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest) {
        movieService.updateMovie(id, movieRequest);
        return ResponseEntity.noContent().build();
    }
}
