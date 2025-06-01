package com.ptithcm.clinic_booking.repository;

import com.ptithcm.clinic_booking.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, String> {
    Optional<Manager> findByAccountUsername(String username);

    Optional<Manager> findByEmail(String email);
}
