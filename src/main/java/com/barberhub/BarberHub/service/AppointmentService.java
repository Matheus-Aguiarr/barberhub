package com.barberhub.BarberHub.service;

import com.barberhub.BarberHub.dto.AppointmentDTO;
import com.barberhub.BarberHub.dto.AppointmentRequestDTO;
import com.barberhub.BarberHub.enums.AppointmentStatus;
import com.barberhub.BarberHub.exceptions.AppointmentNotFoundException;
import com.barberhub.BarberHub.exceptions.ServiceNotFoundException;
import com.barberhub.BarberHub.exceptions.UserNotFoundException;
import com.barberhub.BarberHub.model.AppointmentModel;
import com.barberhub.BarberHub.model.ServiceModel;
import com.barberhub.BarberHub.model.UserModel;
import com.barberhub.BarberHub.repository.AppointmentRepository;
import com.barberhub.BarberHub.repository.ServiceRepository;
import com.barberhub.BarberHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, ServiceRepository serviceRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    public AppointmentDTO createAppointment(AppointmentRequestDTO appointment) {
        AppointmentModel appointmentModel = new AppointmentModel();
        ServiceModel searchService = serviceRepository.findById(appointment.getServiceId()).orElseThrow(ServiceNotFoundException::new);
        UserModel searchUser = userRepository.findById(appointment.getUserId()).orElseThrow(UserNotFoundException::new);
        appointmentModel.setService(searchService);
        appointmentModel.setStatus(AppointmentStatus.valueOf(appointment.getStatus().toUpperCase()));
        appointmentModel.setUser(searchUser);
        appointmentModel.setDateTime(LocalDateTime.parse(appointment.getDateTime()));

        AppointmentModel savedAppointment = appointmentRepository.save(appointmentModel);
        return new AppointmentDTO(savedAppointment);
    }

    public List<AppointmentDTO> getAllAppointments() {
        List<AppointmentModel> appointmentModelList = appointmentRepository.findAll();
        return appointmentModelList.stream().map(AppointmentDTO::new).toList();
    }

    public List<AppointmentDTO> getAppointmentByUserId(Long userId) {
        List<AppointmentModel> searchModels = appointmentRepository.findByUserId(userId);
        return searchModels.stream().map(AppointmentDTO::new).toList();
    }

    public AppointmentDTO updateStatusOfAppointment(Long appointmentId, String status) {
        AppointmentModel searchModel = appointmentRepository.findById(appointmentId).orElseThrow(AppointmentNotFoundException::new);
        searchModel.setStatus(AppointmentStatus.valueOf(status.toUpperCase()));
        AppointmentModel updated = appointmentRepository.save(searchModel);
        return new AppointmentDTO(updated);
    }
}
