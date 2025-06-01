package com.ptithcm.clinic_booking.repository;

import com.ptithcm.clinic_booking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findAllCustomerByStatus(String status);
    List<Customer> findByNameContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name, String phone, String email);
}
