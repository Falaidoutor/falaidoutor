package com.pex.falaidoutor.domain.model.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="queue_triage")
public class QueueTriage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queueTicket;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "triage_id", nullable = true)
    private Triage triage;

    @OneToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusQueue status;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Triage getTriage() {
        return triage;
    }

    public void setTriage(Triage triage) {
        this.triage = triage;
    }

    public String getQueueTicket() {
        return queueTicket;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setQueueTicket(String queueTicket) {
        this.queueTicket = queueTicket;
    }

    public StatusQueue getStatus() {
        return status;
    }

    public void setStatus(StatusQueue status) {
        this.status = status;
    }
}
