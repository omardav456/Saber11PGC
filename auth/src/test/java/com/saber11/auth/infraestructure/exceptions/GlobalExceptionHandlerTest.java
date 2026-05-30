package com.saber11.auth.infraestructure.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void manejarError_ReturnsNotFound_WhenMessageContainsNoEncontrado() {
        ResponseEntity<String> response = handler.manejarError(new RuntimeException("Usuario no encontrado"));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuario no encontrado", response.getBody());
    }

    @Test
    void manejarError_ReturnsUnauthorized_WhenMessageContainsIncorrecta() {
        ResponseEntity<String> response = handler.manejarError(new RuntimeException("Contraseña incorrecta"));
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Contraseña incorrecta", response.getBody());
    }

    @Test
    void manejarError_ReturnsConflict_WhenMessageContainsCorreo() {
        ResponseEntity<String> response = handler.manejarError(new RuntimeException("El correo ya está registrado"));
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("El correo ya está registrado", response.getBody());
    }

    @Test
    void manejarError_ReturnsBadRequest_WhenJpaTransactionError() {
        ResponseEntity<String> response = handler.manejarError(new RuntimeException("Could not commit JPA transaction"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Correo inválido", response.getBody());
    }

    @Test
    void manejarError_ReturnsBadRequest_ForGenericException() {
        ResponseEntity<String> response = handler.manejarError(new RuntimeException("ERROR GENERICO"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error generico", response.getBody());
    }

    @Test
    void manejarError_ReturnsNotFound_WhenMessageContainsNoEncontradoBeforeCorreo() {
        ResponseEntity<String> response = handler.manejarError(new RuntimeException("correo no encontrado"));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("correo no encontrado", response.getBody());
    }
}
