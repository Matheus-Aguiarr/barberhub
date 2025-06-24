package com.barberhub.BarberHub.contoller;

import com.barberhub.BarberHub.dto.appointment.AppointmentDTO;
import com.barberhub.BarberHub.dto.appointment.AppointmentRequestDTO;
import com.barberhub.BarberHub.domain.appointment.AppointmentService;
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

    @GetMapping("appointment/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(appointmentId));
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

    @GetMapping("/appointment/date")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentOfDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentService.getAppointmentOfDate(date));
    }

    @GetMapping("/appointment/confirmed")
    public ResponseEntity<List<AppointmentDTO>> getConfirmedAppointments() {
        return ResponseEntity.ok(appointmentService.getConfirmedAppointments());
    }

    @PutMapping("/appointment/{appointmentId}/status")
    public ResponseEntity<AppointmentDTO> updateStatusOfAppointment(@PathVariable Long appointmentId, @RequestParam String status) {
        return ResponseEntity.ok(appointmentService.updateStatusOfAppointment(appointmentId, status));
    }

    @DeleteMapping("/appointment/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(appointmentId));
    }

    @DeleteMapping("/appointment/{appointmentId}/cancel")
    public ResponseEntity<AppointmentDTO> cancelAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.cancelAppointment(appointmentId));
    }

}
