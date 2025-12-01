package com.systembreak.reporting.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "scans")
public class ScanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scan_id", unique = true, nullable = false)
    private String scanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @Column(name = "collected_at")
    private Instant collectedAt;

    @Column(name = "overall_severity")
    private String overallSeverity;

    public Long getId() {
        return id;
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    public Instant getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(Instant collectedAt) {
        this.collectedAt = collectedAt;
    }

    public String getOverallSeverity() {
        return overallSeverity;
    }

    public void setOverallSeverity(String overallSeverity) {
        this.overallSeverity = overallSeverity;
    }
}
