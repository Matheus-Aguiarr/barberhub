package com.barberhub.BarberHub.dto;

import com.barberhub.BarberHub.model.ServiceModel;
import jakarta.persistence.criteria.CriteriaBuilder;

public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private Double priceInCents;
    private Integer durationInMinutes;

    public ServiceDTO() {}

    public ServiceDTO(ServiceModel service) {
        this.id = service.getId();
        this.name = service.getName();
        this.description = service.getDescription();
        this.priceInCents = service.getPriceInCents();
        this.durationInMinutes = service.getDurationInMinutes();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(Double priceInCents) {
        this.priceInCents = priceInCents;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Long getId() {
        return id;
    }
}
