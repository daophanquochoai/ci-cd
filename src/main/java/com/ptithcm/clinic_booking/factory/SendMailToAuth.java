package com.ptithcm.clinic_booking.factory;

import com.ptithcm.clinic_booking.model.EmailOtp;
import com.ptithcm.clinic_booking.service.EmailOtpService;
import com.ptithcm.clinic_booking.service.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMailToAuth implements ISendMail {
    private final EmailService emailService;
    private final EmailOtpService emailOtpService;

    @Autowired
    public SendMailToAuth(EmailService emailService, EmailOtpService emailOtpService) {
        this.emailService = emailService;
        this.emailOtpService = emailOtpService;
    }

    @Transactional
    @Override
    public void sendOtpToEmail(String email) {
        String otp = emailService.generateOtp();
        String content = "Mã OTP của bạn là: " + otp + ". Vui lòng không chia sẻ mã này với người khác.";
        emailOtpService.saveEmailOtp(email, otp, EmailOtp.OtpPurpose.APPOINTMENT);
        emailService.sendMail(email,"Đặt lịch khám bệnh - Mã OTP xác minh email", content);
    }
}
