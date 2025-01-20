package org.example.cinema.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.response.HallResponse;
import org.example.cinema.service.HallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hall Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/hall")
public class HallController {
    private final HallService hallService;

    @GetMapping
    public ResponseEntity<List<HallResponse>> getHalls() {
        return ResponseEntity.ok(hallService.getHalls());
    }


    @PostMapping("/seat/{id}")
    public ResponseEntity<String> addSeat(@PathVariable Long id, @RequestParam Integer seatNumber) {
        hallService.addSeat(id, seatNumber);
        return ResponseEntity.ok("Əməliyyat uğurla icra edildi");
    }
}
