package com.systembreak.reporting.service;

import com.systembreak.reporting.dto.ScanCompletedEventDto;

public interface ScanEventService {

    void processScanCompleted(ScanCompletedEventDto event, String correlationId);
}
