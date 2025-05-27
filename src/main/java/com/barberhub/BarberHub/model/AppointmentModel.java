package com.barberhub.BarberHub.model;

import com.barberhub.BarberHub.enums.AppointmentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceModel service;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public AppointmentModel(UserModel user, LocalDateTime dateTime, ServiceModel service, AppointmentStatus status) {
        this.user = user;
        this.dateTime = dateTime;
        this.service = service;
        this.status = status;
    }

    public AppointmentModel() {}

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ServiceModel getService() {
        return service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }
}
