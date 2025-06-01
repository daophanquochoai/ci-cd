package com.ptithcm.clinic_booking.controller.manager;

import com.ptithcm.clinic_booking.dto.customer.CustomerDTO;
import com.ptithcm.clinic_booking.model.ApiResponse;
import com.ptithcm.clinic_booking.service.CustomerService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/m/customers")
public class CustomerManageController {

    private final CustomerService customerService;

    @Autowired
    public CustomerManageController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> getAllCustomers(){
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, customers));
    }

    @GetMapping("/all/{status}")
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> getAllCustomersByStatus(@PathVariable @NotBlank String status){
        List<CustomerDTO> customers = customerService.getAllCustomersByStatus(status);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, customers));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> softDeleteCustomer(@PathVariable @NotBlank String id) {
        customerService.softDeleteCustomer(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Xóa khách hàng thành công (soft delete)."));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> searchCustomers(@RequestParam @NotBlank String keyword) {
        List<CustomerDTO> customers = customerService.searchCustomers(keyword);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, customers));
    }



//
//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse<String>> addCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
//        customerService.addCustomer(customerDTO);
//        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED, "Thêm khách hàng thành công."));
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<ApiResponse<String>> updateCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
//        customerService.updateCustomer(customerDTO);
//        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, "Cập nhật khách hàng thành công."));
//    }


}
