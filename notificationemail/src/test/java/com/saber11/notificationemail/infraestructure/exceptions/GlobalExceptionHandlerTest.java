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
}
