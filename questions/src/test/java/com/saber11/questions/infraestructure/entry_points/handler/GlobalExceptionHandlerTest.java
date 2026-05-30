package com.saber11.questions.infraestructure.entry_points.handler;

import com.saber11.questions.infraestructure.entry_points.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleException_ReturnsBadRequest() {
        String errorMessage = "Error de prueba";

        ResponseEntity<ErrorResponse> response = handler.handleException(new RuntimeException(errorMessage));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getCode());
        assertNotNull(response.getBody().getTimestamp());
    }
}
