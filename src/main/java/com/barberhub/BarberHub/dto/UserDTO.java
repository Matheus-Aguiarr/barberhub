package com.barberhub.BarberHub.dto;

import com.barberhub.BarberHub.model.UserModel;

import java.util.Optional;

public record UserDTO(Long id, String name, String email, String phone) {

    public UserDTO(UserModel user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }


}
