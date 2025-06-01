package com.ptithcm.clinic_booking.repository;

import com.ptithcm.clinic_booking.dto.account.AccountResponseDTO;
import com.ptithcm.clinic_booking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
}
