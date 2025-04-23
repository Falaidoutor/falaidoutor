package com.pex.falaidoutor.model.dto;

public class FinalizedTriageDTO {
    private Long queue_id;
    private String name;
    private String gender;
    private Integer age;
    private String queueTicket;
    private String risk;

    public FinalizedTriageDTO(Long queue_id, String name, String gender, Integer age, String queueTicket, String risk) {
        this.queue_id = queue_id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.queueTicket = queueTicket;
        this.risk = risk;
    }

    // Getters e Setters

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getQueueTicket() {
        return queueTicket;
    }

    public String getRisk() {
        return risk;
    }

    public Long getQueue_id() {
        return queue_id;
    }

    public void setQueue_id(Long queue_id) {
        this.queue_id = queue_id;
    }
}
