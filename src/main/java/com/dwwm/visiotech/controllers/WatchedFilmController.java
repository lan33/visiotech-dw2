package com.dwwm.visiotech.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dwwm.visiotech.dtos.CommentRequestDTO;
import com.dwwm.visiotech.dtos.RatingRequestDTO;
import com.dwwm.visiotech.dtos.WatchedFilmRequestDTO;
import com.dwwm.visiotech.entities.WatchedFilm;
import com.dwwm.visiotech.services.WatchedFilmService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/{userId}/watched")
public class WatchedFilmController {

    private final WatchedFilmService watchedFilmService;

    public WatchedFilmController(WatchedFilmService watchedFilmService) {
        this.watchedFilmService = watchedFilmService;
    }

    @PostMapping
    public ResponseEntity<WatchedFilm> addWatched(@PathVariable Long userId,
            @Valid @RequestBody WatchedFilmRequestDTO request) {
        WatchedFilm created = watchedFilmService.addWatched(userId, request.getFilmId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{filmId}")
    public ResponseEntity<Void> deleteWatched(@PathVariable Long userId, @PathVariable Long filmId) {
        watchedFilmService.deleteWatched(userId, filmId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{filmId}/ratings")
    public ResponseEntity<WatchedFilm> setRating(@PathVariable Long userId, @PathVariable Long filmId,
            @Valid @RequestBody RatingRequestDTO request) {
        WatchedFilm updated = watchedFilmService.rateWatchedFilm(userId, filmId, request.getRating());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{filmId}/ratings")
    public ResponseEntity<Void> deleteRating(@PathVariable Long userId, @PathVariable Long filmId) {
        watchedFilmService.deleteRating(userId, filmId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{filmId}/comments")
    public ResponseEntity<WatchedFilm> upsertComment(@PathVariable Long userId, @PathVariable Long filmId,
    @Valid @RequestBody CommentRequestDTO request){
        WatchedFilm updated = watchedFilmService.commentOnWatchedFilm(userId, filmId, request.getComment());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{filmId}/comments")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId, @PathVariable Long filmId) {
        watchedFilmService.deleteComment(userId, filmId);
        return ResponseEntity.noContent().build();
    }
}
