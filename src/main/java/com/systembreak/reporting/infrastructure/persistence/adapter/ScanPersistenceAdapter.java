package com.systembreak.reporting.infrastructure.persistence.adapter;

import com.systembreak.reporting.domain.model.Scan;
import com.systembreak.reporting.domain.ports.out.ScanPersistencePort;
import com.systembreak.reporting.infrastructure.persistence.entity.ScanEntity;
import com.systembreak.reporting.infrastructure.persistence.repository.ScanJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScanPersistenceAdapter implements ScanPersistencePort {

    private final ScanJpaRepository scanJpaRepository;
    // Mapper a implementar
    
    @Override
    public Optional<Scan> findLatestForDevice(String deviceId) {
        return Optional.ofNullable(scanJpaRepository.findFirstByDeviceAgentIdOrderByFinishedAtDesc(deviceId))
                .map(this::toDomainModel);
    }

    @Override
    public List<Scan> findHistoryForDevice(String deviceId) {
        return scanJpaRepository.findByDeviceAgentIdOrderByFinishedAtDesc(deviceId).stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    private Scan toDomainModel(ScanEntity entity) {
        // Conversión simplificada
        return Scan.builder()
                .id(entity.getId())
                .scanCorrelationId(entity.getScanCorrelationId())
                .startedAt(entity.getStartedAt())
                .finishedAt(entity.getFinishedAt())
                .status(entity.getStatus())
                .overallSeverity(entity.getOverallSeverity())
                .totalPackages(entity.getTotalPackages())
                .vulnerablePackages(entity.getVulnerablePackages())
                .rawReport(entity.getRawReport())
                .build();
    }
}
