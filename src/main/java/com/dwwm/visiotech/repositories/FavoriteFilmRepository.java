package com.dwwm.visiotech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwwm.visiotech.entities.FavoriteFilm;
import com.dwwm.visiotech.entities.FavoriteFilm.FavoriteFilmId;

public interface FavoriteFilmRepository extends JpaRepository<FavoriteFilm, FavoriteFilmId> {
}
