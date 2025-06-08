package com.pex.falaidoutor.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "queue_triage")
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
}
