package com.barberhub.BarberHub.repository;

import com.barberhub.BarberHub.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
