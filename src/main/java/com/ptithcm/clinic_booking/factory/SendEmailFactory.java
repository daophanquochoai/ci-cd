package com.ptithcm.clinic_booking.factory;

import com.ptithcm.clinic_booking.model.EmailOtp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendEmailFactory {
    private final SendMailToAuth sendMailToAuth;
    private final SendMailToGetPassword sendMailToGetPassword;

    public SendEmailFactory(SendMailToAuth sendMailToAuth,
                            SendMailToGetPassword sendMailToGetPassword) {
        this.sendMailToAuth = sendMailToAuth;
        this.sendMailToGetPassword = sendMailToGetPassword;
    }
    public ISendMail createISendMail(EmailOtp.OtpPurpose otpPurpose) {
        switch (otpPurpose){
            case APPOINTMENT:
                return sendMailToAuth;
            case ACCOUNT_VERIFY:
                return sendMailToGetPassword;
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}
