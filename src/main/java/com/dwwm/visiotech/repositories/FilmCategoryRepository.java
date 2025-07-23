package com.dwwm.visiotech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwwm.visiotech.entities.FilmCategory;
import com.dwwm.visiotech.entities.FilmCategory.FilmCategoryId;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {
}
