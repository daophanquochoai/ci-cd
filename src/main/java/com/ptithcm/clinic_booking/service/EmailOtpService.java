package com.ptithcm.clinic_booking.service;

import com.ptithcm.clinic_booking.model.EmailOtp;

public interface EmailOtpService {
    void saveEmailOtp(String email, String otp, EmailOtp.OtpPurpose purpose);
    void checkEmailOtp(String email, String otp, EmailOtp.OtpPurpose purpose);
}
