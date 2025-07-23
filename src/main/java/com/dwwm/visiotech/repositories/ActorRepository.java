package com.dwwm.visiotech.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwwm.visiotech.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByNameContainingIgnoreCase(String name);
}
