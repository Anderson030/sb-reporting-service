package com.systembreak.reporting.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "devices")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "endpoint_id", unique = true, nullable = false)
    private String endpointId;

    @Column(name = "last_distro")
    private String lastDistro;

    @Column(name = "last_overall_severity")
    private String lastOverallSeverity;

    public Long getId() {
        return id;
    }

    public String getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(String endpointId) {
        this.endpointId = endpointId;
    }

    public String getLastDistro() {
        return lastDistro;
    }

    public void setLastDistro(String lastDistro) {
        this.lastDistro = lastDistro;
    }

    public String getLastOverallSeverity() {
        return lastOverallSeverity;
    }

    public void setLastOverallSeverity(String lastOverallSeverity) {
        this.lastOverallSeverity = lastOverallSeverity;
    }
}
