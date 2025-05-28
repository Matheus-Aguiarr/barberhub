package com.barberhub.BarberHub.contoller;

import com.barberhub.BarberHub.dto.ServiceDTO;
import com.barberhub.BarberHub.dto.ServiceRequestDTO;
import com.barberhub.BarberHub.model.ServiceModel;
import com.barberhub.BarberHub.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {

    private final ServicesService servicesService;

    @Autowired
    public ServiceController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceDTO>> getServices() {
        return ResponseEntity.ok(servicesService.getServices());
    }


    @GetMapping("/services/{serviceId}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long serviceId) {
        return ResponseEntity.ok(servicesService.getServiceById(serviceId));
    }

    @PostMapping("/services")
    public ResponseEntity<String> createService(@RequestBody ServiceRequestDTO service) {
        return ResponseEntity.ok(servicesService.createService(service));
    }

    @DeleteMapping("/services/{serviceId}")
    public ResponseEntity<String> deleteServiceById(@PathVariable Long serviceId) {
        return ResponseEntity.ok(servicesService.deleteServiceById(serviceId));
    }

}
