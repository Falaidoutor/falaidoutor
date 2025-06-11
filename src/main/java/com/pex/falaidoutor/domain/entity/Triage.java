package com.pex.falaidoutor.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    public Triage(String symptoms, String risk, String justification) {
        this.symptoms = symptoms;
        this.risk = risk;
        this.justification = justification;
    }
}
