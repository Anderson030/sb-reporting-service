package com.systembreak.reporting.repository;

import com.systembreak.reporting.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    Optional<DeviceEntity> findByEndpointId(String endpointId);
}
