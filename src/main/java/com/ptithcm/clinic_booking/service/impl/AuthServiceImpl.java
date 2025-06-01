package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.config.JwtUtil;
import com.ptithcm.clinic_booking.dto.doctor.DoctorResponseDTO;
import com.ptithcm.clinic_booking.dto.doctor.DoctorSimpleResponseDTO;
import com.ptithcm.clinic_booking.dto.manager.ManagerResponseDTO;
import com.ptithcm.clinic_booking.dto.auth.AuthResponseDTO;
import com.ptithcm.clinic_booking.exception.ResourceNotFoundException;
import com.ptithcm.clinic_booking.factory.SendEmailFactory;
import com.ptithcm.clinic_booking.mapper.DoctorMapper;
import com.ptithcm.clinic_booking.mapper.ManagerMapper;
import com.ptithcm.clinic_booking.model.*;
import com.ptithcm.clinic_booking.repository.AccountRepository;
import com.ptithcm.clinic_booking.repository.DoctorRepository;
import com.ptithcm.clinic_booking.repository.ManagerRepository;
import com.ptithcm.clinic_booking.service.AuthService;
import com.ptithcm.clinic_booking.service.EmailOtpService;
import com.ptithcm.clinic_booking.service.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
//    private final EmailService emailService;
    private final SendEmailFactory sendEmailFactory;
    private final EmailOtpService emailOtpService;
    private final PasswordEncoder passwordEncoder;
    private final DoctorRepository doctorRepository;
    private final ManagerRepository managerRepository;

    public AuthServiceImpl(JwtUtil jwtUtil, AuthenticationManager authenticationManager,
                           AccountRepository accountRepository, EmailService emailService, SendEmailFactory sendEmailFactory,
                           EmailOtpService emailOtpService, PasswordEncoder passwordEncoder,
                           DoctorRepository doctorRepository, ManagerRepository managerRepository) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.sendEmailFactory = sendEmailFactory;
//        this.emailService = emailService;
        this.emailOtpService = emailOtpService;
        this.passwordEncoder = passwordEncoder;
        this.doctorRepository = doctorRepository;
        this.managerRepository = managerRepository;
    }


    @Override
    public AuthResponseDTO login(String username, String password) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        UserDetails userDetails =(UserDetails) authentication.getPrincipal();
        String authToken = jwtUtil.generateToken(userDetails);

        AuthResponseDTO authResponse = new AuthResponseDTO();
        
        if (userDetails instanceof DoctorDetails doctorDetails) {
            DoctorResponseDTO doctorDTO = DoctorMapper.toDoctorDTO(doctorDetails.getDoctor());
            authResponse.setUser(doctorDTO);
        } else if (userDetails instanceof ManagerDetails managerDetails) {
            ManagerResponseDTO managerDTO = ManagerMapper.toManagerDTO(managerDetails.getManager());
            authResponse.setUser(managerDTO);
        }
        authResponse.setToken(authToken);
        return authResponse;
    }

    @Override
    public void logout() {

    }

    @Transactional
    @Override
    public void changePassword(String username, String currentPassword, String newPassword) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("Sai tên tài khoản"));
        account.setPassword(newPassword);
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void sendOtpToEmail(String email) {
        boolean exists = doctorRepository.findByEmail(email).isPresent()
                             || managerRepository.findByEmail(email).isPresent();
        if (!exists) throw new IllegalArgumentException("Email này chưa được đăng ký trên hệ thống");

        sendEmailFactory.createISendMail(EmailOtp.OtpPurpose.ACCOUNT_VERIFY).sendOtpToEmail(email);

//        String subject = "Quên mật khẩu - Mã OTP lấy lại mật khẩu";
//        String otp = emailService.generateOtp();
//        String content = "Mã OTP của bạn là: " + otp + "\nVui lòng không chia sẻ mã này với người khác.";

//        emailOtpService.saveEmailOtp(email, otp, EmailOtp.OtpPurpose.ACCOUNT_VERIFY);
//        emailService.sendMail(email, subject, content);
    }

    @Transactional
    @Override
    public void resetPassword(String email, String otp, String newPassword) {
        Doctor doctor = doctorRepository.findByEmail(email).orElse(null);
        Account account;
        if (doctor != null) {
            account = doctor.getAccount();
        } else {
            Manager manager = managerRepository.findByEmail(email).orElse(null);
            if (manager == null)    throw new IllegalArgumentException("Email không hợp lệ");
            account = manager.getAccount();
        }

        emailOtpService.checkEmailOtp(email, otp, EmailOtp.OtpPurpose.ACCOUNT_VERIFY);

        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }

    @Override
    public String verifyOtpEmail(String email, String otp) {
        emailOtpService.checkEmailOtp(email, otp, EmailOtp.OtpPurpose.ACCOUNT_VERIFY);
        return jwtUtil.generateResetPasswordToken(email);
    }

    @Override
    public void resetPasswordByToken(String token, String newPassword) {
        String email = jwtUtil.validateResetPasswordToken(token);
        Doctor doctor = doctorRepository.findByEmail(email).orElse(null);
        Account account;
        if (doctor != null) {
            account = doctor.getAccount();
        } else {
            Manager manager = managerRepository.findByEmail(email).orElse(null);
            if (manager == null)    throw new IllegalArgumentException("Email không hợp lệ");
            account = manager.getAccount();
        }

        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }
}
