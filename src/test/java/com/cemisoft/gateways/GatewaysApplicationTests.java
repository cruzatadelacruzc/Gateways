package com.cemisoft.gateways;

import com.cemisoft.gateways.web.rest.GatewayResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GatewaysApplicationTests {

    @Autowired
    private GatewayResource controller;

    @Test
    @DisplayName("Test GatewayController exists")
    void contextLoads() throws Exception {
        Assertions.assertNotNull(controller);
    }
}
