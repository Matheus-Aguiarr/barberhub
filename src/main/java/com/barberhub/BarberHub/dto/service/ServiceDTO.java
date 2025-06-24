package com.barberhub.BarberHub.dto.service;

import com.barberhub.BarberHub.domain.service.ServiceModel;

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
