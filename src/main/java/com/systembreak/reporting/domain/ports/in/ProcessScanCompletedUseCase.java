package com.systembreak.reporting.domain.ports.in;

import com.systembreak.reporting.domain.model.Scan;

public interface ProcessScanCompletedUseCase {

    Scan processScanCompleted(Scan scan);
}
