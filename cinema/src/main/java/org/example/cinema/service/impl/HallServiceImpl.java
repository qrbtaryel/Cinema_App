package org.example.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.response.HallResponse;
import org.example.cinema.model.Hall;
import org.example.cinema.repository.HallRepository;
import org.example.cinema.repository.SeatRepository;
import org.example.cinema.service.HallService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;
    private final SeatRepository seatRepository;

    @Override
    public List<HallResponse> getHalls() {
        return hallRepository.findAll().stream()
                .map(hall -> HallResponse.builder()
                        .id(hall.getId())
                        .name(hall.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Hall findById(Long hallId) {
        return hallRepository.findById(hallId).orElse(null);
    }

    @Override
    public void addSeat(Long id, Integer seatName) {
        Hall hall = findById(id);
        seatRepository.findByHallAndSeatNumber(hall, seatName);
    }
}
