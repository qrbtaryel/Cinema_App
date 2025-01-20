package org.example.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.request.GenreRequest;
import org.example.cinema.dto.response.GenreResponse;
import org.example.cinema.mapper.GenreMapper;
import org.example.cinema.model.Genre;
import org.example.cinema.repository.GenreRepository;
import org.example.cinema.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreResponse> getGenres() {
        return genreRepository.findAll().stream().map(genreMapper::toGenreResponse).toList();
    }

    @Override
    public List<Genre> getGenres(List<Long> ids) {
        return genreRepository.findAllById(ids);
    }

    @Override
    public List<GenreResponse> saveAll(List<GenreRequest> genreRequests) {
        List<Genre> genres = genreRequests.stream().map(genreMapper::toGenre).collect(Collectors.toList());
        List<Genre> newGenres = genreRepository.saveAll(genres);
        return newGenres.stream().map(genreMapper::toGenreResponse).toList();
    }
}
