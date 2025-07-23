package com.dwwm.visiotech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwwm.visiotech.entities.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    List<User> findByEmail(String email);
}
