package com.cemisoft.gateways.repository;

import com.cemisoft.gateways.domain.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatewayRepository extends JpaRepository<Gateway, Long> {
}
