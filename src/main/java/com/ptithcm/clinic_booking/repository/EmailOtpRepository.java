package com.ptithcm.clinic_booking.repository;

import com.ptithcm.clinic_booking.model.EmailOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailOtpRepository extends JpaRepository<EmailOtp, Long> {
    Optional<EmailOtp> findByEmail(String email);
    Optional<EmailOtp> findByEmailAndPurpose(String email, EmailOtp.OtpPurpose purpose);
    Optional<EmailOtp> findByEmailAndOtpAndPurpose(String email, String otp, EmailOtp.OtpPurpose purpose);
}
