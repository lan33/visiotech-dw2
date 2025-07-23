package com.dwwm.visiotech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwwm.visiotech.entities.WatchedFilm;
import com.dwwm.visiotech.entities.WatchedFilm.WatchedFilmId;

public interface WatchedFilmRepository extends JpaRepository<WatchedFilm, WatchedFilmId> {
}
