package com.ptithcm.clinic_booking.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor  extends Person{

    @Id
    @Column(name = "id", length = 10)
    private String id;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_specialty_id", nullable = false)
    private MedicalSpecialty medicalSpecialty;

//    @Column(name = "name", nullable = false, length = 100)
//    private String name;
//
//    @Column(name = "phone", nullable = false, unique = true, length = 15)
//    private String phone;
//
//    @Column(name = "email", nullable = false, unique = true, length = 100)
//    private String email;
//
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;
//
//    @Column(name = "address", length = 255)
//    private String address;
//
//    @Column(name = "gender")
//    private Boolean gender;

    @Column(name = "qualification", columnDefinition = "TEXT")
    private String qualification;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_link", length = 255)
    private String imageLink;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

//    @CreationTimestamp
//    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME")
//    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "doctor")
    private List<Schedule> schedules;

    public Doctor() {
        super();
    }

    public Doctor(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public MedicalSpecialty getMedicalSpecialty() {
        return medicalSpecialty;
    }

    public void setMedicalSpecialty(MedicalSpecialty medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
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

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
