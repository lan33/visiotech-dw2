package com.dwwm.visiotech.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dwwm.visiotech.entities.User;
import com.dwwm.visiotech.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<User> users = userRepository.findByEmail(email);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Email introuvable : " + email);
        }

        User user = users.get(0);

        return new UserPrincipal(user);
    }
}
