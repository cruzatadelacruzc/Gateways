package com.cemisoft.gateways.repository;

import com.cemisoft.gateways.domain.Peripheral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PeripheralRepository extends JpaRepository<Peripheral, UUID> {
}
