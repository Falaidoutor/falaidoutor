package com.pex.falaidoutor.domain.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "triage")
public class Triage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symptoms", nullable = false, length = 1000)
    private String symptoms;

    @Column(name = "risk", nullable = false, length = 1000)
    private String risk;

    @Column(name = "justification", nullable = false, length = 1000)
    private String justification;

    public Triage() {
    }

    public Triage(String symptoms, String risk, String justification) {
        this.symptoms = symptoms;
        this.risk = risk;
        this.justification = justification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }
}
