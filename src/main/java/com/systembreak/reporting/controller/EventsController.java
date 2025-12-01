package com.systembreak.reporting.controller;

import com.systembreak.reporting.dto.ScanCompletedEventDto;
import com.systembreak.reporting.service.ScanEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/events")
public class EventsController {

    private final ScanEventService scanEventService;

    public EventsController(ScanEventService scanEventService) {
        this.scanEventService = scanEventService;
    }

    @PostMapping("/scan-completed")
    public ResponseEntity<Void> handleScanCompleted(
            @RequestBody ScanCompletedEventDto event,
            @RequestHeader(name = "X-Correlation-Id", required = false) String correlationId
    ) {
        scanEventService.processScanCompleted(event, correlationId);
        return ResponseEntity.accepted().build();
    }
}
