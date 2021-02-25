package com.cemisoft.gateways.service;

import com.cemisoft.gateways.domain.Gateway;
import com.cemisoft.gateways.repository.GatewayRepository;
import com.cemisoft.gateways.service.dto.GatewayDTO;
import com.cemisoft.gateways.service.mapper.GatewayMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GatewayService {

    private final Logger log = LoggerFactory.getLogger(GatewayService.class);

    private final GatewayRepository gatewayRepository;

    private final GatewayMapper gatewayMapper;

    public GatewayService(GatewayRepository gatewayRepository, GatewayMapper gatewayMapper) {
        this.gatewayRepository = gatewayRepository;
        this.gatewayMapper = gatewayMapper;
    }

    public GatewayDTO save(GatewayDTO gatewayDTO) {
        log.debug("Request to save Gateway : {}", gatewayDTO);
        Gateway gateway = gatewayMapper.toEntity(gatewayDTO);
        gateway = gatewayRepository.save(gateway);
        return gatewayMapper.toDto(gateway);
    }

    public Page<GatewayDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Gateways");
        return gatewayRepository.findAll(pageable)
                .map(gatewayMapper::toDto);
    }
}
