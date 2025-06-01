package com.ptithcm.clinic_booking.exception;

public class ResourceAlreadyExistsException  extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}