package com.dwwm.visiotech.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dwwm.visiotech.entities.Film;
import com.dwwm.visiotech.repositories.FilmRepository;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public List<Film> searchFilms(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title);
    }
}
