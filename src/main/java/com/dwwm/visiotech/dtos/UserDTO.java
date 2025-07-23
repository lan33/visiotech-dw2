package com.dwwm.visiotech.dtos;

import com.dwwm.visiotech.entities.User;

public class UserDTO {

    private Long userId;

    public UserDTO(User user) {
        this.userId = user.getUserId();
    }

    public Long getUserId() {
        return userId;
    }
}
