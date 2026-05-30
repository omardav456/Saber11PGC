package com.saber11.auth.infraestructure.security_encrypter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncrypterGatewayImplTest {

    private final EncrypterGatewayImpl encrypterGateway = new EncrypterGatewayImpl();

    @Test
    void encrypt_ReturnsNonNullEncodedString() {
        String encoded = encrypterGateway.encrypt("password123");
        assertNotNull(encoded);
        assertFalse(encoded.isBlank());
        assertNotEquals("password123", encoded);
    }

    @Test
    void matches_ReturnsTrue_WhenPasswordMatches() {
        String encoded = encrypterGateway.encrypt("password123");
        assertTrue(encrypterGateway.matches("password123", encoded));
    }

    @Test
    void matches_ReturnsFalse_WhenPasswordDoesNotMatch() {
        String encoded = encrypterGateway.encrypt("password123");
        assertFalse(encrypterGateway.matches("wrongPassword", encoded));
    }
}
