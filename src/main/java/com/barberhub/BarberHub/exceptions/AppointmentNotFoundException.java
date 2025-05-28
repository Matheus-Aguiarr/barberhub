package com.barberhub.BarberHub.exceptions;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }

    public AppointmentNotFoundException() {
      super("Appointment Not Found.");
    }
}
