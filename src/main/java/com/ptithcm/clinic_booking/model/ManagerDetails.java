package com.ptithcm.clinic_booking.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class ManagerDetails implements UserDetails {
    private final Manager manager;

    public ManagerDetails(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + manager.getAccount().getRole().getName().toUpperCase()));

    }

    @Override
    public String getPassword() {
        return this.manager.getAccount().getPassword();
    }

    @Override
    public String getUsername() {
        return this.manager.getAccount().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return "ACTIVE".equalsIgnoreCase(this.manager.getAccount().getStatus());
    }
}
