package com.dwwm.visiotech.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdatePasswordRequestDTO {

    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;

    public UpdatePasswordRequestDTO() {}

    public String getPassword() {
        return password;
    }   
}
