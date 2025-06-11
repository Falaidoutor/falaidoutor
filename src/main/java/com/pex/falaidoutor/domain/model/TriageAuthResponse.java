package com.pex.falaidoutor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TriageAuthResponse {
    private boolean authenticated;
    private String patientName;
    private Long queueTriageId;
    private Long statusId;
}
