package com.ptithcm.clinic_booking.service;

import com.ptithcm.clinic_booking.dto.account.AccountRequestDTO;
import com.ptithcm.clinic_booking.dto.account.AccountResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public  interface AccountService {
    AccountResponseDTO getAccountById(String username);

    void addAccount(AccountRequestDTO accountRequestDTO);
    void updateAccount(AccountRequestDTO accountRequestDTO);
    void softDeleteAccount(String username);

    void changePassword(String username,String currentPassword, String newPassword);

    Page<AccountResponseDTO> getAllAccounts(Pageable pageable) ;
    boolean existsByUsername(String username);
}
