package com.dwwm.visiotech.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dwwm.visiotech.dtos.UpdatePasswordRequestDTO;
import com.dwwm.visiotech.dtos.UserDTO;
import com.dwwm.visiotech.entities.User;
import com.dwwm.visiotech.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<UserDTO> updatePassword(@PathVariable Long userId,
            @Valid @RequestBody UpdatePasswordRequestDTO request) {
        User updated = userService.updatePassword(userId, request.getPassword());
        return ResponseEntity.ok(new UserDTO(updated));
    }
}
