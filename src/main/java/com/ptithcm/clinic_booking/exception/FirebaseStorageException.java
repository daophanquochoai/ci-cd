package com.ptithcm.clinic_booking.exception;

public class FirebaseStorageException extends RuntimeException {
    public FirebaseStorageException(String message) {
        super(message);
    }

    public FirebaseStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}