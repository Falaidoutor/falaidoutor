package com.pex.falaidoutor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TriageListDTO {
    private Long queue_id;
    private String name;
    private String gender;
    private Integer age;
    private String queueTicket;
    private String risk;
}
