package com.ptithcm.clinic_booking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ptithcm.clinic_booking.model.Clinic;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, String> {
    List<Clinic> findByStatus(String status);
    Page<Clinic> findByStatus(String status, Pageable pageable);
}
