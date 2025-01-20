package org.example.cinema.repository;

import org.example.cinema.model.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {
    void deleteAllByMovieId(Long movieId);
}
