package org.example.cinema.repository;

import org.example.cinema.model.MovieSession;
import org.example.cinema.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsAllByMovieSession(MovieSession movieSession);

    List<Ticket> findAllByMovieSession(MovieSession movieSession);
    List<Ticket> findAllByUserId(Long userId);
}
