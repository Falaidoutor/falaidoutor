package com.pex.falaidoutor.domain.model.dto;

public class TriageAuthResponse {
    private boolean authenticated;
    private String patientName;
    private Long queueTriageId;
    private Long statusId;

    public TriageAuthResponse(boolean authenticated, String patientName, Long queueTriageId, Long status) {
        this.authenticated = authenticated;
        this.patientName = patientName;
        this.queueTriageId = queueTriageId;
        this.statusId = status;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getPatientName() {
        return patientName;
    }

    public Long getQueueTriageId() {
        return queueTriageId;
    }

    public Long getStatus() {
        return statusId;
    }

    public void setStatus(Long status) {
        this.statusId = status;
    }
}
