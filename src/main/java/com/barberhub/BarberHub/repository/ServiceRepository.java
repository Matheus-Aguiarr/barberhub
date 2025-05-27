package com.barberhub.BarberHub.repository;

import com.barberhub.BarberHub.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {
}
