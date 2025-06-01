package com.ptithcm.clinic_booking.service.impl;

import com.ptithcm.clinic_booking.model.*;
import com.ptithcm.clinic_booking.repository.AccountRepository;
import com.ptithcm.clinic_booking.repository.DoctorRepository;
import com.ptithcm.clinic_booking.repository.ManagerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final DoctorRepository doctorRepository;
    private final ManagerRepository managerRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository, DoctorRepository doctorRepository, ManagerRepository managerRepository) {
        this.accountRepository = accountRepository;
        this.doctorRepository = doctorRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String roleName = account.getRole().getName();
        switch (roleName) {
            case "DOCTOR":
                Doctor doctor = doctorRepository.findByAccountUsername(username)
                        .orElseThrow(() -> new RuntimeException("Doctor not found"));
                return new DoctorDetails(doctor);
            case "MANAGER":
                Manager manager = managerRepository.findByAccountUsername(username)
                        .orElseThrow(() -> new RuntimeException("Manager not found"));
                return new ManagerDetails(manager);

            default:
                throw new RuntimeException("Unsupported role: " + roleName);
        }
    }
}
