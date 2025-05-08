package com.pex.falaidoutor.domain.model.dto;

import lombok.Data;

@Data
public class TriageDTO {
    private String symptoms;
    private String risk;
    private String justification;

    public TriageDTO(String symptoms, String risk, String justification) {
        this.symptoms = symptoms;
        this.risk = risk;
        this.justification = justification;
    }
}