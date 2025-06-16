package com.barberhub.BarberHub.dto;

import com.barberhub.BarberHub.model.ServiceModel;
import org.apache.catalina.Service;

import java.security.Provider;

public record ServiceDTO(Long id, String name, String description, Double priceInCents, Integer durationInMinutes) {
    public ServiceDTO(ServiceModel service) {
        this(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPriceInCents(),
                service.getDurationInMinutes()
        );
    }
}
