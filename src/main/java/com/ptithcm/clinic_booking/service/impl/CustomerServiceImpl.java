package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.dto.appointment.AppointmentDTO;
import com.ptithcm.clinic_booking.dto.customer.CustomerDTO;
import com.ptithcm.clinic_booking.dto.customer.CustomerRequestDTO;
import com.ptithcm.clinic_booking.exception.ResourceNotFoundException;
import com.ptithcm.clinic_booking.factory.SendEmailFactory;
import com.ptithcm.clinic_booking.mapper.AppointmentMapper;
import com.ptithcm.clinic_booking.mapper.CustomerMapper;
import com.ptithcm.clinic_booking.model.Appointment;
import com.ptithcm.clinic_booking.model.Customer;
import com.ptithcm.clinic_booking.model.EmailOtp;
import com.ptithcm.clinic_booking.repository.AppointmentRepository;
import com.ptithcm.clinic_booking.repository.CustomerRepository;
import com.ptithcm.clinic_booking.service.CustomerService;
import com.ptithcm.clinic_booking.service.EmailOtpService;
import com.ptithcm.clinic_booking.service.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailService emailService;
    private final EmailOtpService emailOtpService;
    private final AppointmentRepository appointmentRepository;
    private final SendEmailFactory sendEmailFactory;

    public CustomerServiceImpl(CustomerRepository customerRepository, EmailService emailService,
                               EmailOtpService emailOtpService, AppointmentRepository appointmentRepository,
                               SendEmailFactory sendEmailFactory) {
        this.customerRepository = customerRepository;
        this.emailService = emailService;
        this.emailOtpService = emailOtpService;
        this.appointmentRepository = appointmentRepository;
        this.sendEmailFactory = sendEmailFactory;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(CustomerMapper::toCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getAllCustomersByStatus(String status) {
        List<Customer> customers = customerRepository.findAllCustomerByStatus(status);
        return customers.stream()
                .map(CustomerMapper::toCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository
                .findByNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        keyword, keyword, keyword);

        return customers.stream()
                .map(CustomerMapper::toCustomerDTO) // bạn cần có CustomerMapper
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(String id) {
        Customer customer = customerRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + id));
        return CustomerMapper.toCustomerDTO(customer);
    }

    @Override
    public CustomerDTO addCustomer(CustomerRequestDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomer(customerDTO);
        customer.setStatus("ACTIVE"); // hoặc trạng thái mặc định
        customerRepository.save(customer);
        return CustomerMapper.toCustomerDTO(customer);
    }

    @Transactional
    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerRepository.findById(customerDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + customerDTO.getId()));
        Customer customer = CustomerMapper.toCustomer(customerDTO);
        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void softDeleteCustomer(String id) {
        Customer customer = customerRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + id));
        customer.setStatus("DELETED");
        customerRepository.save(customer);
    }

    @Override
    public void sendOtpToEmail(String email) {
//        String otp = emailService.generateOtp();
//        String content = "Mã OTP của bạn là: " + otp + ". Vui lòng không chia sẻ mã này với người khác.";
//        emailOtpService.saveEmailOtp(email, otp, EmailOtp.OtpPurpose.APPOINTMENT);
//        emailService.sendMail(email,"Đặt lịch khám bệnh - Mã OTP xác minh email",content);
        sendEmailFactory.createISendMail(EmailOtp.OtpPurpose.APPOINTMENT).sendOtpToEmail(email);
    }

    @Override
    public void authEmail(String email, String otp) {
        emailOtpService.checkEmailOtp(email, otp, EmailOtp.OtpPurpose.APPOINTMENT);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByCustomerId(String customerId) {
        int id;
        try {
            id = Integer.parseInt(customerId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID khách hàng không hợp lệ.");
        }

        List<Appointment> appointments = appointmentRepository.findAllByCustomerId(id);
        return appointments.stream()
                .map(AppointmentMapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }
}
