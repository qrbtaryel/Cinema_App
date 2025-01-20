package org.example.cinema.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.request.GenreRequest;
import org.example.cinema.dto.response.GenreResponse;
import org.example.cinema.model.Genre;
import org.example.cinema.service.GenreService;
import org.example.cinema.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Genre Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreResponse>> getGenres() {
        return ResponseEntity.ok(genreService.getGenres());
    }


    @PostMapping
    public ResponseEntity<List<GenreResponse>> saveAll(@RequestBody List<GenreRequest> genres) {
        return ResponseEntity.ok(genreService.saveAll(genres));
    }

}
