package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.model.EmailOtp;
import com.ptithcm.clinic_booking.repository.EmailOtpRepository;
import com.ptithcm.clinic_booking.service.EmailOtpService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailOtpServiceImpl implements EmailOtpService {
    private final EmailOtpRepository emailOtpRepository;

    public EmailOtpServiceImpl(EmailOtpRepository emailOtpRepository) {
        this.emailOtpRepository = emailOtpRepository;
    }

    @Transactional
    @Override
    public void saveEmailOtp(String email, String otp, EmailOtp.OtpPurpose purpose) {
        EmailOtp emailOtp = emailOtpRepository.findByEmailAndPurpose(email, purpose).orElse(null);
        if(emailOtp != null) {
            if (emailOtp.getCreatedAt() != null
                    && emailOtp.getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(1))
                    && !emailOtp.isVerified()) {
                throw new IllegalStateException("Vui lòng chờ ít nhất 1 phút trước khi yêu cầu mã OTP mới");
            }
        }else{
            emailOtp = new EmailOtp();
            emailOtp.setEmail(email);
        }
        emailOtp.setOtp(otp);
        emailOtp.setVerified(false);
        emailOtp.setCreatedAt(LocalDateTime.now());
        emailOtp.setExpireAt(emailOtp.getCreatedAt().plusMinutes(5));
        emailOtp.setPurpose(purpose);
        emailOtpRepository.save(emailOtp);
    }

    @Override
    public void checkEmailOtp(String email, String otp, EmailOtp.OtpPurpose purpose) {
        EmailOtp emailOtp = emailOtpRepository.findByEmailAndOtpAndPurpose(email, otp, purpose)
                .orElseThrow(() -> new IllegalArgumentException("Email hoặc OTP không hợp lệ"));
        if (emailOtp.isVerified())
            throw new IllegalStateException("OTP này đã được sử dụng.");

        if (emailOtp.getExpireAt().isBefore(LocalDateTime.now()))
            throw new IllegalStateException("OTP đã hết hạn.");

        emailOtp.setVerified(true);
        emailOtpRepository.save(emailOtp);
    }
}
