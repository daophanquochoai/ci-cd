package com.ptithcm.clinic_booking.service;

public interface EmailService {
    void sendMail(String to, String subject, String content);
    String generateOtp();
}