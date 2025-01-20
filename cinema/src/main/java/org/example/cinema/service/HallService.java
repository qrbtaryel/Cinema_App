package org.example.cinema.service;

import org.example.cinema.dto.response.HallResponse;
import org.example.cinema.model.Hall;

import java.util.List;

public interface HallService {
    Hall findById(Long hallId);

    List<HallResponse> getHalls();

    void addSeat(Long id, Integer seatNumber);
}
