package com.ptithcm.clinic_booking.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @Column(name = "id", length = 10)
    private String id;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Manager creator;

    @ManyToOne
    @JoinColumn(name ="medical_specialty_id", nullable = false)
    private MedicalSpecialty medicalSpecialty;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "service")
    private List<Appointment> appointments;

    public Service() {
    }

    public Service(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Manager getCreator() {
        return creator;
    }

    public void setCreator(Manager creator) {
        this.creator = creator;
    }

    public MedicalSpecialty getMedicalSpecialty() {
        return medicalSpecialty;
    }

    public void setMedicalSpecialty(MedicalSpecialty medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
