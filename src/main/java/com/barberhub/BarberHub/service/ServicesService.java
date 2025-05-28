package com.barberhub.BarberHub.service;

import com.barberhub.BarberHub.dto.ServiceDTO;
import com.barberhub.BarberHub.dto.ServiceRequestDTO;
import com.barberhub.BarberHub.exceptions.ServiceNotFoundException;
import com.barberhub.BarberHub.model.ServiceModel;
import com.barberhub.BarberHub.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicesService {
    private final ServiceRepository serviceRepository;


    @Autowired
    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    public String createService(ServiceRequestDTO service) {
        ServiceModel serviceModel = new ServiceModel();

        serviceModel.setName(service.getName());
        serviceModel.setDescription(service.getDescription());
        serviceModel.setDurationInMinutes(service.getDurationInMinutes());
        serviceModel.setPriceInCents(service.getPriceInCents());
        serviceRepository.save(serviceModel);

        return "Servico criado com sucesso.";
    }

    public List<ServiceDTO> getServices() {
        List<ServiceModel> serviceModels = serviceRepository.findAll();
        return serviceModels.stream().map(ServiceDTO::new).toList();
    }

    public ServiceDTO getServiceById(Long serviceId) {
        ServiceModel serviceModel = serviceRepository.findById(serviceId).orElseThrow(ServiceNotFoundException::new);
        return new ServiceDTO(serviceModel);
    }
}
