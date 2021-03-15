package com.cemisoft.gateways.service.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PeripheralDTOTest {
    @Test
    @DisplayName("PeripheralDTO Equals Verifier")
    void dtoEqualsVerifier() throws Exception {
        EqualsVerifier.forClass(PeripheralDTO.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }
}
