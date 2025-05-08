package com.pex.falaidoutor.domain.model.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FinalizedTriageDTO {
    private Long queue_id;
    private String name;
    private char gender;
    private Integer age;
    private String queueTicket;
    private String symptoms;
    private String risk;
    private String justification;
    private String createdAtDate;
    private String createdAtTime;

    public FinalizedTriageDTO(Long queue_id, String name, char gender, Integer age, String queueTicket, String symptoms, String risk, String justification, LocalDateTime createdAt) {
        this.queue_id = queue_id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.queueTicket = queueTicket;
        this.symptoms = symptoms;
        this.risk = risk;
        this.justification = justification;
        this.createdAtDate = createdAt.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        this.createdAtTime = createdAt.toLocalTime().withNano(0).toString();
    }

    // Getters e Setters


    public Long getQueue_id() {
        return queue_id;
    }

    public void setQueue_id(Long queue_id) {
        this.queue_id = queue_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getQueueTicket() {
        return queueTicket;
    }

    public void setQueueTicket(String queueTicket) {
        this.queueTicket = queueTicket;
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

    public String getCreatedAtDate() {
        return createdAtDate;
    }

    public void setCreatedAtDate(String createdAtDate) {
        this.createdAtDate = createdAtDate;
    }

    public String getCreatedAtTime() {
        return createdAtTime;
    }

    public void setCreatedAtTime(String createdAtTime) {
        this.createdAtTime = createdAtTime;
    }
}
