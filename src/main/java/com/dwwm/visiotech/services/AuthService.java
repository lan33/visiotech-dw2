package com.dwwm.visiotech.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dwwm.visiotech.entities.Role;
import com.dwwm.visiotech.entities.User;
import com.dwwm.visiotech.repositories.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(email, encodedPassword, Role.USER);
        return userRepository.save(user);
    }
}
