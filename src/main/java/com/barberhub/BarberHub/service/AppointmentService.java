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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public List<LocalTime> getAvailableTimes(Long serviceId, LocalDate date) {
//        Aqui estao sendo definidos manualmente todos os horarios possiveis de atendimento nesse servico
        List<LocalTime> possibleTimes = List.of(
                LocalTime.of(8, 0),
                LocalTime.of(8, 30),
                LocalTime.of(9, 0),
                LocalTime.of(9, 30),
                LocalTime.of(10, 0),
                LocalTime.of(10, 30),
                LocalTime.of(11, 0),
                LocalTime.of(11, 30),
                LocalTime.of(13, 0),
                LocalTime.of(13, 30),
                LocalTime.of(14, 0),
                LocalTime.of(14, 30),
                LocalTime.of(15, 0),
                LocalTime.of(15, 30),
                LocalTime.of(16, 0)
        );

//        Busca os appointments ja marcados nesse dia e servico
        List<AppointmentModel> appointments = appointmentRepository.findByServiceIdAndDate(serviceId, date);
//      Pega todos os horarios desses appointments que ja estao ocupados. No caso -> apenas transforma os appointments ignorando a data, para retornar apenas HORARIOS.
        List<LocalTime> occupiedTimes = appointments.stream().map(a -> a.getDateTime().toLocalTime()).toList();
//          Tras apenas os horarios que nao estao na lista de ocupados, ou seja -> se contem time em occupiedTimes, NAO LISTE ELES.
        return possibleTimes.stream().filter(time -> !occupiedTimes.contains(time)).toList();
    }

    public String deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
        return "Appointment deleted";
    }

    public AppointmentDTO cancelAppointment(Long appointmentId) {
        AppointmentModel searchAppointment = appointmentRepository.findById(appointmentId).orElseThrow(AppointmentNotFoundException::new);
        searchAppointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(searchAppointment);
        return new AppointmentDTO(searchAppointment);
    }

    public AppointmentDTO getAppointmentById(Long appointmentId) {
        AppointmentModel searchAppointment = appointmentRepository.findById(appointmentId).orElseThrow(AppointmentNotFoundException::new);
        return new AppointmentDTO(searchAppointment);
    }

    public List<AppointmentDTO> getAppointmentOfDate(LocalDate date) {
        List<AppointmentModel> appointments = appointmentRepository.findByDate(date);
        return appointments.stream()
                    .map(AppointmentDTO::new)
                        .toList();
    }

    public List<AppointmentDTO> getConfirmedAppointments() {
        List<AppointmentModel> searchAppointment = appointmentRepository.findByStatus(AppointmentStatus.CONFIRMED);
        return searchAppointment.stream()
                    .map(AppointmentDTO::new)
                        .toList();
    }
}
