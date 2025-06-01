package com.ptithcm.clinic_booking.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @Column(name = "name", nullable = false, length = 100)
//    private String name;
//
//    @Column(name = "phone", nullable = false, length = 15)
//    private String phone;
//
//    @Column(name = "email", nullable = false, length = 100)
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

    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
