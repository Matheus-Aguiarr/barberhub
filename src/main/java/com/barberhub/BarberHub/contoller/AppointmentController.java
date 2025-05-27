package com.barberhub.BarberHub.contoller;

import com.barberhub.BarberHub.dto.AppointmentDTO;
import com.barberhub.BarberHub.dto.AppointmentRequestDTO;
import com.barberhub.BarberHub.repository.AppointmentRepository;
import com.barberhub.BarberHub.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @PostMapping("/appointment")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentRequestDTO appointment) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointment));
    }
}
