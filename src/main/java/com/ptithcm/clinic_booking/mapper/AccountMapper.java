package com.ptithcm.clinic_booking.mapper;

import com.ptithcm.clinic_booking.dto.RoleDTO;
import com.ptithcm.clinic_booking.dto.account.AccountRequestDTO;
import com.ptithcm.clinic_booking.dto.account.AccountResponseDTO;
import com.ptithcm.clinic_booking.model.Account;
import com.ptithcm.clinic_booking.model.Role;

public class AccountMapper {
    public static AccountResponseDTO toAccountResponseDTO(Account account) {
        if (account == null) return null;

        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setUsername(account.getUsername());
        Role role = account.getRole();
        dto.setRole(new RoleDTO(role.getId(), role.getName(), role.getStatus()));
        dto.setStatus(account.getStatus());
        dto.setCreatedAt(account.getCreatedAt());

        return dto;
    }

    public static AccountRequestDTO toAccountRequestDTO(Account account) {
        if (account == null) return null;

        AccountRequestDTO dto = new AccountRequestDTO();
        dto.setUsername(account.getUsername());
        dto.setPassword(account.getPassword());
        dto.setRoleId(account.getRole().getId());
        dto.setStatus(account.getStatus());
        return dto;
    }
    public static Account toAccount(AccountRequestDTO dto) {
        if (dto == null) return null;

        Account account = new Account();
        account.setUsername(dto.getUsername());
        account.setPassword(dto.getPassword());
        account.setRole(new Role(dto.getRoleId()));
        account.setStatus(dto.getStatus());
        return account;
    }
    public static Account toAccount(AccountResponseDTO dto) {
        if (dto == null) return null;

        Account account = new Account();
        account.setUsername(dto.getUsername());
        account.setRole(new Role(dto.getRole().getId()));
        account.setStatus(dto.getStatus());
        return account;
    }
}
