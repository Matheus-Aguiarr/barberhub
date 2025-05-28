package com.barberhub.BarberHub.repository;

import com.barberhub.BarberHub.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {
    List<AppointmentModel> findByUserId(Long userId);
}
