package org.example.cinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.request.SessionRequest;
import org.example.cinema.dto.response.SessionResponse;
import org.example.cinema.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Session Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;

    @Operation(summary = "Sessiya-ları gətirmək ucun GET API")
    @GetMapping
    public ResponseEntity<List<SessionResponse>> getSessions() {

        return ResponseEntity.ok(sessionService.getSessions());
    }

    @Operation(summary = "Sessiya əlavə etmək ucun POST API")
    @PostMapping
    public ResponseEntity<SessionResponse> save(@Valid @RequestBody SessionRequest sessionRequest) {
        return ResponseEntity.ok(sessionService.createSession(sessionRequest));
    }
}
