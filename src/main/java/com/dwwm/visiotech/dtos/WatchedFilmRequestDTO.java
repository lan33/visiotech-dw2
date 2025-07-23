package com.dwwm.visiotech.dtos;

import jakarta.validation.constraints.NotNull;

public class WatchedFilmRequestDTO {

    @NotNull(message = "L’identifiant du film ne peut pas être null")
    private Long filmId;

    public WatchedFilmRequestDTO() {
    }

    public Long getFilmId() {
        return filmId;
    }
}
