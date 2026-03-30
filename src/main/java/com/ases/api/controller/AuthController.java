package com.ases.api.controller;

import com.ases.api.dtos.AuthResponse;
import com.ases.api.dtos.LoginDTO;
import com.ases.api.dtos.TokenResponse;
import com.ases.api.dtos.UserRegistrationDTO;
import com.ases.api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRegistrationDTO registrationDto) {
        String userId = authService.register(registrationDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/{id}")
                .buildAndExpand(userId)
                .toUri();

        AuthResponse authResponse = new AuthResponse(userId);
        return ResponseEntity.created(location).body(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginDTO loginDto) {
        String token = authService.login(loginDto);
        TokenResponse tokenResponse = new TokenResponse(token);
        return ResponseEntity.ok(tokenResponse);
    }
}
