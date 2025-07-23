package com.dwwm.visiotech.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(FavoriteFilm.FavoriteFilmId.class)
public class FavoriteFilm {

    @Id
    private Long userId;

    @Id
    private Long filmId;

    public static class FavoriteFilmId implements Serializable {
        private Long userId;
        private Long filmId;

        public FavoriteFilmId() {
        }

        public FavoriteFilmId(Long userId, Long filmId) {
            this.userId = userId;
            this.filmId = filmId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof FavoriteFilmId that))
                return false;
            return Objects.equals(userId, that.userId) && Objects.equals(filmId, that.filmId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, filmId);
        }
    }

    public FavoriteFilm() {
    }

    public FavoriteFilm(Long userId, Long filmId) {
        this.userId = userId;
        this.filmId = filmId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }
}
