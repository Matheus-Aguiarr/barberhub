package com.barberhub.BarberHub.domain.service;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class ServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double priceInCents;
    private Integer durationInMinutes;

    public ServiceModel() {}

    public ServiceModel(String name, String description, Double priceInCents, Integer durationInMinutes) {
        this.name = name;
        this.description = description;
        this.priceInCents = priceInCents;
        this.durationInMinutes = durationInMinutes;
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
