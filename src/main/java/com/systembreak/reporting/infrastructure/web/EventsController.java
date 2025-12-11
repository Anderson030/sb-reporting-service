package com.systembreak.reporting.infrastructure.web;

import com.systembreak.reporting.application.dto.ScanCompletedEventDto;
import com.systembreak.reporting.application.dto.ScanResponseDto;
import com.systembreak.reporting.domain.model.Device;
import com.systembreak.reporting.domain.model.Scan;
import com.systembreak.reporting.domain.ports.in.ProcessScanCompletedUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsController {

    private final ProcessScanCompletedUseCase processScanCompletedUseCase;

    @PostMapping("/scan-completed")
    public ResponseEntity<ScanResponseDto> handleScanCompleted(
            @RequestBody ScanCompletedEventDto dto) {

        Device device = Device.builder()
                .agentId(dto.getAgentId())
                .hostname(dto.getHostname())
                .ipAddress(dto.getIpAddress())
                .operatingSystem(dto.getOperatingSystem())
                .build();

        Scan scan = Scan.builder()
                .device(device)
                .scanCorrelationId(dto.getCorrelationId())
                .startedAt(dto.getStartedAt())
                .finishedAt(dto.getFinishedAt())
                .status(dto.getStatus())
                .overallSeverity(dto.getOverallSeverity())
                .totalPackages(dto.getTotalPackages())
                .vulnerablePackages(dto.getVulnerablePackages())
                .rawReport(dto.getRawReport())
                .build();

        Scan saved = processScanCompletedUseCase.processScanCompleted(scan);

        return ResponseEntity.ok(ScanResponseDto.fromDomain(saved));
    }
}
