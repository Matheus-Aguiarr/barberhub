package com.barberhub.BarberHub.domain.service;

import com.barberhub.BarberHub.dto.service.ServiceDTO;
import com.barberhub.BarberHub.dto.service.ServiceRequestDTO;
import com.barberhub.BarberHub.infra.exceptions.ServiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {
    private final ServiceRepository serviceRepository;


    @Autowired
    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    public ServiceModel createService(ServiceRequestDTO service) {
        ServiceModel serviceModel = new ServiceModel();

        serviceModel.setName(service.getName());
        serviceModel.setDescription(service.getDescription());
        serviceModel.setDurationInMinutes(service.getDurationInMinutes());
        serviceModel.setPriceInCents(service.getPriceInCents());
        serviceRepository.save(serviceModel);

        return  serviceModel;
    }

    public Page<ServiceDTO> getServices(Pageable pageable) {
        var page = serviceRepository.findAll(pageable).map(ServiceDTO::new);
        return page;
    }

    public ServiceDTO getServiceById(Long serviceId) {
        ServiceModel serviceModel = serviceRepository.findById(serviceId).orElseThrow(ServiceNotFoundException::new);
        return new ServiceDTO(serviceModel);
    }

    public String deleteServiceById(Long serviceId) {
        serviceRepository.deleteById(serviceId);
        return "Service deleted with success";
    }

    public ServiceDTO updateServiceById(Long serviceId, ServiceRequestDTO requestDTO) {
        ServiceModel searchService = serviceRepository.findById(serviceId).orElseThrow(ServiceNotFoundException::new);
        searchService.setPriceInCents(requestDTO.getPriceInCents());
        searchService.setDescription(requestDTO.getDescription());
        searchService.setName(requestDTO.getName());
        searchService.setDurationInMinutes(requestDTO.getDurationInMinutes());
        serviceRepository.save(searchService);

        return new ServiceDTO(searchService);

    }
}
