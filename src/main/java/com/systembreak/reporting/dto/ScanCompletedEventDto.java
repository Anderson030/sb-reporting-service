package com.systembreak.reporting.dto;

import java.time.Instant;
import java.util.List;

public class ScanCompletedEventDto {

    private String eventType;
    private String correlationId;
    private String scanId;
    private String endpointId;
    private String distro;
    private Instant collectedAt;

    private List<PackageResultDto> packages;
    private AnalysisDto analysis;



    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
    }

    public String getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(String endpointId) {
        this.endpointId = endpointId;
    }

    public String getDistro() {
        return distro;
    }

    public void setDistro(String distro) {
        this.distro = distro;
    }

    public Instant getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(Instant collectedAt) {
        this.collectedAt = collectedAt;
    }

    public List<PackageResultDto> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageResultDto> packages) {
        this.packages = packages;
    }

    public AnalysisDto getAnalysis() {
        return analysis;
    }

    public void setAnalysis(AnalysisDto analysis) {
        this.analysis = analysis;
    }
}
