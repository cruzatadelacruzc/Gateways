package com.cemisoft.gateways.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GatewayTest {

    @Test
    @DisplayName("Equals Verifier")
    void equalsVerifier() {
        EqualsVerifier.forClass(Gateway.class)
                .verify();
    }
}
