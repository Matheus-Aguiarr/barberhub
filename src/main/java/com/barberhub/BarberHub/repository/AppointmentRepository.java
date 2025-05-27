package com.barberhub.BarberHub.repository;

import com.barberhub.BarberHub.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {
}
