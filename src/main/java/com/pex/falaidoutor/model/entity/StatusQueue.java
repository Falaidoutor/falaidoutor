package com.pex.falaidoutor.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "status_queue")
public class StatusQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", nullable = false)
    private String statusName;


    public StatusQueue(Long id) {
        this.id = id;
    }

    public StatusQueue() {

    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
