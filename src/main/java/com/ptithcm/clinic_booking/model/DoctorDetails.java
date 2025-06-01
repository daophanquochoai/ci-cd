package com.ptithcm.clinic_booking.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class DoctorDetails implements UserDetails {
    private final Doctor doctor;

    public DoctorDetails(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + doctor.getAccount().getRole().getName().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return doctor.getAccount().getPassword();
    }

    @Override
    public String getUsername() {
        return doctor.getAccount().getUsername();
    }
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        return "ACTIVE".equalsIgnoreCase(doctor.getAccount().getStatus());
    }
}
