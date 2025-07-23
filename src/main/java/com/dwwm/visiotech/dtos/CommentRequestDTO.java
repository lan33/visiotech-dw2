package com.dwwm.visiotech.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentRequestDTO {

    @NotBlank(message = "Le commentaire est requis")
    @Size(min = 5, max = 255)
    private String comment;

    public CommentRequestDTO() {
    }

    public String getComment() {
        return comment;
    }
}
