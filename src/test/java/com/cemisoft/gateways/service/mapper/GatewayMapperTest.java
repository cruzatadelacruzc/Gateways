package com.cemisoft.gateways.service.mapper;

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
    @DisplayName("should Mapped Entity id be Equal to given Id")
    void shouldMappedEntityIdBeEqualToGivenId() throws Exception {
        Long id = 1L;
        Assertions.assertEquals(gatewayMapper.fromId(id).getId(), id);
        Assertions.assertNull(gatewayMapper.fromId(null));
    }
}
