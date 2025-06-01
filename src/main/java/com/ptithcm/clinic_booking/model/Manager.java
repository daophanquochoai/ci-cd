package com.ptithcm.clinic_booking.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

@Entity
@Table(name = "manager")
public class Manager extends Person {

    @Id
    @Column(name = "id", length = 10)
    private String id;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

//    @Column(name = "name", nullable = false, length = 100)
//    private String name;
//
//    @Column(name = "phone", nullable = false, unique = true, length = 15)
//    private String phone;
//
//    @Column(name = "email", nullable = false, unique = true, length = 100)
//    private String email;
//
//    @Column(name = "address", length = 255)
//    private String address;
//
//    @Column(name = "gender")
//    private Boolean gender;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

//    @CreationTimestamp
//    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME")
//    private LocalDateTime createdAt;

    public Manager() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Boolean getGender() {
//        return gender;
//    }
//
//    public void setGender(Boolean gender) {
//        this.gender = gender;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
}
