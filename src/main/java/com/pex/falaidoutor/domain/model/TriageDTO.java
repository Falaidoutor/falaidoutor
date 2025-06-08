package com.pex.falaidoutor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TriageDTO {
    private String symptoms;
    private String risk;
    private String justification;
}