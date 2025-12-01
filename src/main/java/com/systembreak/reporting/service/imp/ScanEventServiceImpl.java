package com.systembreak.reporting.service.impl;

import com.systembreak.reporting.dto.ScanCompletedEventDto;
import com.systembreak.reporting.entity.DeviceEntity;
import com.systembreak.reporting.entity.ScanEntity;
import com.systembreak.reporting.repository.DeviceRepository;
import com.systembreak.reporting.repository.ScanRepository;
import com.systembreak.reporting.service.ScanEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScanEventServiceImpl implements ScanEventService {

    private final DeviceRepository deviceRepository;
    private final ScanRepository scanRepository;

    public ScanEventServiceImpl(DeviceRepository deviceRepository,
                                ScanRepository scanRepository) {
        this.deviceRepository = deviceRepository;
        this.scanRepository = scanRepository;
    }

    @Override
    @Transactional
    public void processScanCompleted(ScanCompletedEventDto event, String correlationId) {

        // 1. Buscar o crear el dispositivo
        DeviceEntity device = deviceRepository
                .findByEndpointId(event.getEndpointId())
                .orElseGet(() -> {
                    DeviceEntity d = new DeviceEntity();
                    d.setEndpointId(event.getEndpointId());
                    return d;
                });

        device.setLastDistro(event.getDistro());
        if (event.getAnalysis() != null) {
            device.setLastOverallSeverity(event.getAnalysis().getOverallSeverity());
        }
        deviceRepository.save(device);


        if (scanRepository.findByScanId(event.getScanId()).isPresent()) {
            System.out.println("Scan con scanId=" + event.getScanId() + " ya existe. correlationId=" + correlationId);
            return;
        }


        ScanEntity scan = new ScanEntity();
        scan.setScanId(event.getScanId());
        scan.setDevice(device);
        scan.setCollectedAt(event.getCollectedAt());

        if (event.getAnalysis() != null) {
            scan.setOverallSeverity(event.getAnalysis().getOverallSeverity());
        }

        scanRepository.save(scan);

        System.out.println("SCAN_COMPLETED procesado. correlationId=" + correlationId);
    }
}
