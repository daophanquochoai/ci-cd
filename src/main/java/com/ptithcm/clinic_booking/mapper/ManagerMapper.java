package com.ptithcm.clinic_booking.mapper;

import com.ptithcm.clinic_booking.dto.manager.ManagerRequestDTO;
import com.ptithcm.clinic_booking.dto.manager.ManagerResponseDTO;
import com.ptithcm.clinic_booking.model.Account;
import com.ptithcm.clinic_booking.model.Manager;
import com.ptithcm.clinic_booking.model.Role;

public class ManagerMapper {
    public static ManagerResponseDTO toManagerDTO(Manager manager){
        if(manager == null) return null;
        ManagerResponseDTO dto = new ManagerResponseDTO();
        dto.setId(manager.getId());
        dto.setAccount(AccountMapper.toAccountResponseDTO(manager.getAccount()));
        dto.setName(manager.getName());
        dto.setPhone(manager.getPhone());
        dto.setEmail(manager.getEmail());
        dto.setAddress(manager.getAddress());
        dto.setGender(manager.getGender());
        dto.setStatus(manager.getStatus());
        dto.setCreatedAt(manager.getCreatedAt());
        return dto;
    }
    public static Manager toManager(ManagerRequestDTO dto) {
        if(dto == null) return null;
        Manager manager = new Manager();
//        manager.setId(dto.getId());
        manager.setName(dto.getName());
        manager.setPhone(dto.getPhone());
        manager.setEmail(dto.getEmail());
        manager.setAddress(dto.getAddress());
        manager.setGender(dto.getGender());
        manager.setStatus(dto.getStatus());

        manager.setAccount(AccountMapper.toAccount(dto.getAccount()));
        return manager;
    }

    public static Manager toManager(ManagerResponseDTO dto) {
        if(dto == null) return null;
        Manager manager = new Manager();
        manager.setId(dto.getId());
        manager.setName(dto.getName());
        manager.setPhone(dto.getPhone());
        manager.setEmail(dto.getEmail());
        manager.setAddress(dto.getAddress());
        manager.setGender(dto.getGender());
        manager.setStatus(dto.getStatus());

        manager.setAccount(AccountMapper.toAccount(dto.getAccount()));
        return manager;
    }
}
