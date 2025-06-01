package com.ptithcm.clinic_booking.exception;

public class MailSendException extends RuntimeException {
    public MailSendException(String message, Throwable cause) {
        super(message, cause);
    }
}