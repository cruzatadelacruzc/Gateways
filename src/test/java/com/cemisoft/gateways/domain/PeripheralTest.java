package com.cemisoft.gateways.domain;

import com.cemisoft.gateways.GatewaysApplication;
import com.cemisoft.gateways.domain.enumeration.Status;
import com.cemisoft.gateways.repository.PeripheralRepository;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest(classes = GatewaysApplication.class)
@Transactional
public class PeripheralTest {

    @Autowired
    private PeripheralRepository peripheralRepository;

    @Test
    @DisplayName("Equals Verifier")
    void equalsVerifier() {
        EqualsVerifier.forClass(Peripheral.class)
                .verify();
    }

    @Test
    @Transactional
    public void shouldPersistCategoryEnumConvertedValue() {
        UUID id = UUID.randomUUID();

        Peripheral peripheral = new Peripheral();

        peripheral.setStatus(Status.ONLINE);

        peripheralRepository.saveAndFlush(peripheral);

        Peripheral persistedPeripheral = peripheralRepository.findById(peripheral.getId()).orElse(null);

        Assertions.assertEquals(Status.ONLINE, persistedPeripheral.getStatus());
    }
}
