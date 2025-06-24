package com.barberhub.BarberHub.dto.user;

import com.barberhub.BarberHub.domain.user.UserModel;

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
