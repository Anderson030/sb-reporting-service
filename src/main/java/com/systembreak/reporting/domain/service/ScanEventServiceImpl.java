package com.systembreak.reporting.domain.service;

import com.systembreak.reporting.domain.model.Scan;
import com.systembreak.reporting.domain.ports.in.ProcessScanCompletedUseCase;
import com.systembreak.reporting.domain.ports.in.QueryReportsUseCase;
import com.systembreak.reporting.domain.ports.out.LoadReportsPort;
import com.systembreak.reporting.domain.ports.out.SaveScanPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScanEventServiceImpl implements
        ProcessScanCompletedUseCase,
        QueryReportsUseCase {

    private final SaveScanPort saveScanPort;
    private final LoadReportsPort loadReportsPort;

    @Override
    public Scan processScanCompleted(Scan scan) {
        return saveScanPort.saveScan(scan);
    }

    @Override
    public Optional<Scan> getLatestScanForDevice(String agentId) {
        return loadReportsPort.loadLatestScanForDevice(agentId);
    }

    @Override
    public List<Scan> getScanHistoryForDevice(String agentId) {
        return loadReportsPort.loadScanHistoryForDevice(agentId);
    }
}
