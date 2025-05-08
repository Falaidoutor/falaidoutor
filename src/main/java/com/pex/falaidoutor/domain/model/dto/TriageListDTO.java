package com.pex.falaidoutor.domain.model.dto;

public class TriageListDTO {

    private Long queue_id;
    private String name;
    private String gender;
    private Integer age;
    private String queueTicket;
    private String risk;

    public TriageListDTO(Long queue_id, String name, String gender, Integer age, String queueTicket, String risk) {
        this.queue_id = queue_id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.queueTicket = queueTicket;
        this.risk = risk;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
