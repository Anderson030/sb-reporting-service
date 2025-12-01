package com.systembreak.reporting.repository;

import com.systembreak.reporting.entity.ScanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScanRepository extends JpaRepository<ScanEntity, Long> {

    Optional<ScanEntity> findByScanId(String scanId);
}
