package com.barberhub.BarberHub.dto;

import com.barberhub.BarberHub.enums.AppointmentStatus;
import com.barberhub.BarberHub.model.AppointmentModel;

import java.time.LocalDateTime;

public record AppointmentDTO(Long id, Long userId, Long serviceId, LocalDateTime dateTime, AppointmentStatus status) {

    public AppointmentDTO(AppointmentModel appointmentModel) {
        this(
            appointmentModel.getId(),
            appointmentModel.getUser().getId(),
            appointmentModel.getService().getId(),
            appointmentModel.getDateTime(),
            appointmentModel.getStatus()
        );
    }
}
