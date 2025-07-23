package com.dwwm.visiotech.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwwm.visiotech.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByTitleContainingIgnoreCase(String title);
}
