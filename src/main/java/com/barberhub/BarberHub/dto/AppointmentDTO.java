package com.barberhub.BarberHub.dto;

import com.barberhub.BarberHub.enums.AppointmentStatus;
import com.barberhub.BarberHub.model.AppointmentModel;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private Long id;
    private Long userId;
    private Long serviceId;
    private LocalDateTime dateTime;
    private AppointmentStatus status;

    public AppointmentDTO() {}

    public AppointmentDTO(AppointmentModel appointment) {
        this.id = appointment.getId();
        this.userId = appointment.getUser().getId();
        this.serviceId = appointment.getService().getId();
        this.dateTime = appointment.getDateTime();
        this.status = appointment.getStatus();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }
}
