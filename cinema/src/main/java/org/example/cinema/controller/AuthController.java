package org.example.cinema.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.request.AuthRequest;
import org.example.cinema.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.example.cinema.config.JwtGenerateUtil.REFRESH_TOKEN;

@Tag(name = "Auth Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Istifadeci girisi ucun POST API")
    @PostMapping("/login")
    public  ResponseEntity<Void> login(
            @Valid @RequestBody AuthRequest authRequest,
            HttpServletResponse httpResponse
    ) {
        authService.login(authRequest, httpResponse);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> refresh(@CookieValue(REFRESH_TOKEN) String refreshToken, HttpServletResponse httpResponse) {
        authService.refresh(refreshToken, httpResponse);
        return ResponseEntity.noContent().build();
    }
}
