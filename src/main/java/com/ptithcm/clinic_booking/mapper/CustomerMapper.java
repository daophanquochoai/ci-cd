package com.ptithcm.clinic_booking.mapper;

import com.ptithcm.clinic_booking.dto.customer.CustomerDTO;
import com.ptithcm.clinic_booking.dto.customer.CustomerRequestDTO;
import com.ptithcm.clinic_booking.model.Customer;

public class CustomerMapper {
    public static CustomerDTO toCustomerDTO(Customer customer){
        if (customer == null) return null;

        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhone(customer.getPhone());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        dto.setGender(customer.getGender());
        dto.setStatus(customer.getStatus());
        dto.setCreatedAt(customer.getCreatedAt());

        return dto;
    }

    public static Customer toCustomer(CustomerDTO dto){
        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        customer.setGender(dto.getGender());
        customer.setStatus(dto.getStatus());

        return customer;
    }

    public static Customer toCustomer(CustomerRequestDTO dto){
        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        customer.setGender(dto.getGender());

        return customer;
    }
}
