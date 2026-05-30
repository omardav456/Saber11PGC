package com.saber11.notificationemail.infraestructure.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void manejarErrorWithObligatorioReturnsBadRequest() {
        ResponseEntity<String> response =
                handler.manejarError(new RuntimeException("Campo obligatorio"));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Campo obligatorio", response.getBody());
    }

    @Test
    void manejarErrorWithCorreoReturnsBadRequest() {
        ResponseEntity<String> response =
                handler.manejarError(new RuntimeException("Error con correo destino"));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Error con correo destino", response.getBody());
    }

    @Test
    void manejarErrorWithSmtpReturnsInternalServerError() {
        ResponseEntity<String> response =
                handler.manejarError(new RuntimeException("smtp connection failed"));

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Error con servidor de correo", response.getBody());
    }

    @Test
    void manejarErrorWithGenericMessageReturnsInternalServerError() {
        ResponseEntity<String> response =
                handler.manejarError(new RuntimeException("Algo salio mal"));

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Algo salio mal", response.getBody());
    }

    @Test
    void manejarErrorWithMailServerReturns500() {
        // Arrange
        RuntimeException ex = new RuntimeException("Error with mail server");

        // Act
        ResponseEntity<String> response = handler.manejarError(ex);

        // Assert
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Error con servidor de correo", response.getBody());
    }

    @Test
    void manejarErrorWithAuthenticationFailedReturns500() {
        // Arrange
        RuntimeException ex = new RuntimeException("authentication failed");

        // Act
        ResponseEntity<String> response = handler.manejarError(ex);

        // Assert
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Error con servidor de correo", response.getBody());
    }

    @Test
    void manejarErrorWithPlantillaReturns500() {
        // Arrange
        RuntimeException ex = new RuntimeException("Error cargando plantilla: foo");

        // Act
        ResponseEntity<String> response = handler.manejarError(ex);

        // Assert
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Error con servidor de correo", response.getBody());
    }

    @Test
    void manejarErrorWithEnviandoCorreoReturns500() {
        // Arrange
        RuntimeException ex = new RuntimeException("Error enviando correo: timeout");

        // Act
        ResponseEntity<String> response = handler.manejarError(ex);

        // Assert
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Error enviando correo: timeout", response.getBody());
    }

    @Test
    void manejarErrorWithNullMessageReturns500() {
        // Act & Assert
        Assertions.assertThrows(NullPointerException.class,
                () -> handler.manejarError(new RuntimeException((String) null)));
    }

    @Test
    void manejarErrorWithEmptyMessageReturns500() {
        // Arrange
        RuntimeException ex = new RuntimeException("");

        // Act
        ResponseEntity<String> response = handler.manejarError(ex);

        // Assert
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("", response.getBody());
    }
}
