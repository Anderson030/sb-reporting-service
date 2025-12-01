package com.systembreak.reporting.dto;

import java.util.List;

public class AnalysisDto {

    private String overallSeverity; // LOW | MEDIUM | HIGH | CRITICAL
    private List<VulnerabilityDto> vulnerabilities;

    public String getOverallSeverity() {
        return overallSeverity;
    }

    public void setOverallSeverity(String overallSeverity) {
        this.overallSeverity = overallSeverity;
    }

    public List<VulnerabilityDto> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(List<VulnerabilityDto> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }
}
