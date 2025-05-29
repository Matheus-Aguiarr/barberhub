package com.barberhub.BarberHub.contoller;

import com.barberhub.BarberHub.dto.AppointmentDTO;
import com.barberhub.BarberHub.dto.AppointmentRequestDTO;
import com.barberhub.BarberHub.repository.AppointmentRepository;
import com.barberhub.BarberHub.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointment")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/appointment/userId/{userId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentByUserId(@PathVariable Long userId) {
       return ResponseEntity.ok(appointmentService.getAppointmentByUserId(userId));
    }

//    Metodo que serve para retornar uma lista de horarios disponiveis, dado um ID de um servico e uma data. request => GET appointment/available-times?serviceId=1&date=2025-05-29
    @GetMapping("/appointment/available-times")
//    Espera a data no formato YYYY-MM-DD (year, month, day)
    public ResponseEntity<List<LocalTime>> getAvailableTimes(@RequestParam Long serviceId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentService.getAvailableTimes(serviceId, date));
    }

    @PostMapping("/appointment")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentRequestDTO appointment) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointment));
    }

    @PutMapping("/appointment/{appointmentId}/status")
    public ResponseEntity<AppointmentDTO> updateStatusOfAppointment(@PathVariable Long appointmentId, @RequestParam String status) {
        return ResponseEntity.ok(appointmentService.updateStatusOfAppointment(appointmentId, status));
    }

}
