package com.ptithcm.clinic_booking.model;

import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
