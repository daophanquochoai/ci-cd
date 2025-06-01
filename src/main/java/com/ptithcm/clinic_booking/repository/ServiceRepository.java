package com.ptithcm.clinic_booking.repository;

import com.ptithcm.clinic_booking.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, String> {
    List<Service> findAllByStatus(String status);
    Page<Service> findAllByStatus(String status, Pageable pageable);
}
