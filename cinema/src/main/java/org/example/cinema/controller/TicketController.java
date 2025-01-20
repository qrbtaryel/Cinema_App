package org.example.cinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.request.TicketRequest;
import org.example.cinema.dto.response.TicketResponse;
import org.example.cinema.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ticket Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/{movieSessionId}")
    public ResponseEntity<List<TicketResponse>> getTickets(@PathVariable Long movieSessionId) {

        return ResponseEntity.ok(ticketService.getTickets(movieSessionId));
    }

    @GetMapping("/user-tickets")
    public ResponseEntity<List<TicketResponse>> getTickets() {

        return ResponseEntity.ok(ticketService.getTickets());
    }

    @PostMapping("/{movieSessionId}")
    public ResponseEntity<String> createTickets(@PathVariable Long movieSessionId) {
        ticketService.createTickets(movieSessionId);
        return ResponseEntity.ok("Əməliyyat uğurla icra edildi");
    }


    @Operation(summary = "Bilet almaq ucun POST API")
    @PostMapping("/buy/{id}")
    public ResponseEntity<TicketResponse> buyTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.buyTicket(id));
    }

    @Operation(summary = "Bilet qaytarmaq ucun POST API")
    @PostMapping("/return/{id}")
    public ResponseEntity<String> returnTicket(@PathVariable Long id) {
        ticketService.returnTicket(id);
        return ResponseEntity.ok("Əməliyyat uğurla icra edildi");
    }
}
