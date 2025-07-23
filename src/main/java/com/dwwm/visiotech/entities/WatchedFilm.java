package com.dwwm.visiotech.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(WatchedFilm.WatchedFilmId.class)
public class WatchedFilm {

    @Id
    private Long userId;

    @Id
    private Long filmId;

    private Integer rating;

    private LocalDate watchDate;

    private String comment;

    public static class WatchedFilmId implements Serializable {
        private Long userId;
        private Long filmId;

        public WatchedFilmId() {
        }

        public WatchedFilmId(Long userId, Long filmId) {
            this.userId = userId;
            this.filmId = filmId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof WatchedFilmId that))
                return false;
            return Objects.equals(userId, that.userId) && Objects.equals(filmId, that.filmId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, filmId);
        }
    }

    public WatchedFilm() {
    }

    public WatchedFilm(Long userId, Long filmId) {
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDate getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(LocalDate watchDate) {
        this.watchDate = watchDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
