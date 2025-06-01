package com.ptithcm.clinic_booking.controller;

import com.ptithcm.clinic_booking.dto.appointment.AppointmentDTO;
import com.ptithcm.clinic_booking.dto.customer.CustomerDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.service.CustomerService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/p/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> getCustomerById(@PathVariable @NotBlank String id){
        CustomerDTO customer  = customerService.getCustomerById(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, customer));
    }

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<String>> sendOtpToEmail(@RequestParam @Email String email) {
        customerService.sendOtpToEmail(email);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Gửi mã OTP thành công."));
    }

    @PostMapping("/auth-email")
    public ResponseEntity<ApiResponse<String>> authEmail(@RequestParam @Email String email, @RequestParam @NotBlank String otp) {
        customerService.authEmail(email, otp);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Xác thực email thành công."));
    }

    @GetMapping("/{customerId}/appointments")
    public ResponseEntity<ApiResponse<List<AppointmentDTO>>> getAppointmentsByCustomerId(@PathVariable @NotBlank String customerId) {
        List<AppointmentDTO> appointments = customerService.getAppointmentsByCustomerId(customerId);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, appointments));
    }


}
