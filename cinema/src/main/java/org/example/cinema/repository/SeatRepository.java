package org.example.cinema.repository;


import org.example.cinema.model.Hall;
import org.example.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findByHallAndSeatNumber(Hall hall, Integer seatName);
}
