package com.dwwm.visiotech.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dwwm.visiotech.entities.Film;
import com.dwwm.visiotech.services.FilmService;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> films = filmService.getAllFilms();
        return ResponseEntity.ok(films);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Film>> searchFilms(@RequestParam String title) {
        List<Film> films = filmService.searchFilms(title);
        return ResponseEntity.ok(films);
    }
}
