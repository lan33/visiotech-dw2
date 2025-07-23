package com.dwwm.visiotech.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(FilmCategory.FilmCategoryId.class)
public class FilmCategory {

    @Id
    private Long filmId;

    @Id
    private Long categoryId;

    public static class FilmCategoryId implements Serializable {
        private Long filmId;
        private Long categoryId;

        public FilmCategoryId() {
        }

        public FilmCategoryId(Long filmId, Long categoryId) {
            this.filmId = filmId;
            this.categoryId = categoryId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof FilmCategoryId that))
                return false;
            return Objects.equals(filmId, that.filmId) && Objects.equals(categoryId, that.categoryId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(filmId, categoryId);
        }
    }

    public FilmCategory() {
    }

    public FilmCategory(Long filmId, Long categoryId) {
        this.filmId = filmId;
        this.categoryId = categoryId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
