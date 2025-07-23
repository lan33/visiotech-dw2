package com.dwwm.visiotech.services;

import org.springframework.stereotype.Service;

import com.dwwm.visiotech.entities.WatchedFilm;
import com.dwwm.visiotech.entities.WatchedFilm.WatchedFilmId;
import com.dwwm.visiotech.repositories.WatchedFilmRepository;

@Service
public class WatchedFilmService {

    private final WatchedFilmRepository watchedFilmRepository;

    public WatchedFilmService(WatchedFilmRepository watchedFilmRepository) {
        this.watchedFilmRepository = watchedFilmRepository;
    }

    public WatchedFilm addWatched(Long userId, Long filmId) {
        if (watchedFilmRepository.existsById(new WatchedFilmId(userId, filmId))) {
            throw new IllegalArgumentException("Ce film a déjà été marqué comme visionné");
        }

        WatchedFilm watchedFilm = new WatchedFilm(userId, filmId);
        return watchedFilmRepository.save(watchedFilm);
    }

    public void deleteWatched(Long userId, Long filmId) {
        WatchedFilmId id = new WatchedFilmId(userId, filmId);
        if (!watchedFilmRepository.existsById(id)) {
            throw new IllegalArgumentException("Ce film n’a pas été marqué comme visionné");
        }

        watchedFilmRepository.deleteById(id);
    }

    public WatchedFilm rateWatchedFilm(Long userId, Long filmId, Integer rating) {
        WatchedFilm watchedFilm = watchedFilmRepository.findById(new WatchedFilmId(userId, filmId))
                .orElseThrow(() -> new IllegalArgumentException("Ce film n’a pas été marqué comme visionné"));

        watchedFilm.setRating(rating);
        return watchedFilmRepository.save(watchedFilm);
    }

    public WatchedFilm updateRating(Long userId, Long filmId, Integer newRating) {
        return rateWatchedFilm(userId, filmId, newRating);
    }

    public WatchedFilm deleteRating(Long userId, Long filmId) {
        return rateWatchedFilm(userId, filmId, null);
    }

    public WatchedFilm commentOnWatchedFilm(Long userId, Long filmId, String comment) {
        WatchedFilm watchedFilm = watchedFilmRepository.findById(new WatchedFilmId(userId, filmId))
                .orElseThrow(() -> new IllegalArgumentException("Ce film n’a pas été marqué comme visionné"));

        watchedFilm.setComment(comment);
        return watchedFilmRepository.save(watchedFilm);
    }

    public WatchedFilm updateComment(Long userId, Long filmId, String newComment) {
        return commentOnWatchedFilm(userId, filmId, newComment);
    }

    public WatchedFilm deleteComment(Long userId, Long filmId) {
        return commentOnWatchedFilm(userId, filmId, null);
    }
}
