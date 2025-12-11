package com.systembreak.reporting.infrastructure.web;

import com.systembreak.reporting.application.dto.ScanResponseDto;
import com.systembreak.reporting.domain.model.Scan;
import com.systembreak.reporting.domain.ports.in.QueryReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final QueryReportsUseCase queryReportsUseCase;

    @GetMapping("/devices/{agentId}/scans/latest")
    public ResponseEntity<ScanResponseDto> getLatestScan(@PathVariable String agentId) {
        return queryReportsUseCase.getLatestScanForDevice(agentId)
                .map(ScanResponseDto::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/devices/{agentId}/scans/history")
    public ResponseEntity<List<ScanResponseDto>> getScanHistory(@PathVariable String agentId) {
        List<Scan> scans = queryReportsUseCase.getScanHistoryForDevice(agentId);
        List<ScanResponseDto> response = scans.stream()
                .map(ScanResponseDto::fromDomain)
                .toList();
        return ResponseEntity.ok(response);
    }
}
