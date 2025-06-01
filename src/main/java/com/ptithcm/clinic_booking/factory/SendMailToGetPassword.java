package com.ptithcm.clinic_booking.factory;

import com.ptithcm.clinic_booking.model.EmailOtp;
import com.ptithcm.clinic_booking.service.EmailOtpService;
import com.ptithcm.clinic_booking.service.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMailToGetPassword implements ISendMail {
    private final EmailService emailService;
    private final EmailOtpService emailOtpService;

    @Autowired
    public SendMailToGetPassword(EmailService emailService, EmailOtpService emailOtpService) {
        this.emailService = emailService;
        this.emailOtpService = emailOtpService;
    }

    @Transactional
    @Override
    public void sendOtpToEmail(String email) {
        String subject = "Quên mật khẩu - Mã OTP lấy lại mật khẩu";
        String otp = emailService.generateOtp();
        String content = "Mã OTP của bạn là: " + otp + "\nVui lòng không chia sẻ mã này với người khác.";
        emailService.sendMail(email, subject, content);
        emailOtpService.saveEmailOtp(email, otp, EmailOtp.OtpPurpose.ACCOUNT_VERIFY);
    }
}
