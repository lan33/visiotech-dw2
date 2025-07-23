package com.dwwm.visiotech.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.dwwm.visiotech.dtos.RegisterRequestDTO;
import com.dwwm.visiotech.dtos.UserDTO;
import com.dwwm.visiotech.entities.User;
import com.dwwm.visiotech.services.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("auth/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        User created = authService.register(request.getEmail(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(created));
    }
}
