package com.barberhub.BarberHub.contoller;

import com.barberhub.BarberHub.dto.service.ServiceDTO;
import com.barberhub.BarberHub.dto.service.ServiceRequestDTO;
import com.barberhub.BarberHub.domain.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ServiceController {

    private final ServicesService servicesService;

    @Autowired
    public ServiceController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping("/services")
    public ResponseEntity<Page<ServiceDTO>> getServices(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(servicesService.getServices(pageable));
    }


    @GetMapping("/services/{serviceId}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long serviceId) {
        return ResponseEntity.ok(servicesService.getServiceById(serviceId));
    }

    @PostMapping("/services")
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceRequestDTO service, UriComponentsBuilder uriComponentsBuilder) {
        var serviceModel = servicesService.createService(service);

        var uri = uriComponentsBuilder.path("/services/{id}").buildAndExpand(serviceModel.getId()).toUri();
        return ResponseEntity.created(uri).body(new ServiceDTO(serviceModel));
    }

    @DeleteMapping("/services/{serviceId}")
    public ResponseEntity<String> deleteServiceById(@PathVariable Long serviceId) {
        return ResponseEntity.ok(servicesService.deleteServiceById(serviceId));
    }

    @PutMapping("/services/{serviceId}")
    public ResponseEntity<ServiceDTO> updateServiceById(@PathVariable Long serviceId, @RequestBody ServiceRequestDTO requestDTO) {
        return ResponseEntity.ok(servicesService.updateServiceById(serviceId, requestDTO));
    }

}
