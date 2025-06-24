package com.barberhub.BarberHub.infra.exceptions;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String message) {
        super(message);
    }

    public ServiceNotFoundException() {
      super("Service Not Found");
    }
}
