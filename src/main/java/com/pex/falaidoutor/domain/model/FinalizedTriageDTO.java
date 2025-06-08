package com.pex.falaidoutor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
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
}
