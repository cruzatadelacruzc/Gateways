package com.cemisoft.gateways.service.dto;

import com.cemisoft.gateways.service.mapper.GatewayMapper;
import com.cemisoft.gateways.service.mapper.GatewayMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GatewayMapperTest {

    private GatewayMapper gatewayMapper;

    @BeforeEach
    public void setUp() {
        gatewayMapper = new GatewayMapperImpl();
    }

    @Test
    @DisplayName("Test Gateway From Id")
    void testGatewayFromId() {
        Long id = 1L;
        Assertions.assertEquals(gatewayMapper.fromId(id).getId(), id);
        Assertions.assertNull(gatewayMapper.fromId(null));
    }
}
