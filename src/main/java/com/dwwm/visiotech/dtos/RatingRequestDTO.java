package com.dwwm.visiotech.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RatingRequestDTO {

    @NotNull(message = "La note ne peut pas être nulle")
    @Min(value = 1, message = "La note doit être égale ou supérieure à 1")
    @Max(value = 10, message = "La note doit être égale ou inférieure à 10")
    private Integer rating;

    public RatingRequestDTO() {
    }

    public Integer getRating() {
        return rating;
    }    
}
