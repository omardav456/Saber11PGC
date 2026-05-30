package com.saber11.exam.infraestructure.entry_points.handler;

import com.saber11.exam.infraestructure.entry_points.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleExceptionReturnsBadRequestWithMessage() {
        RuntimeException exception = new RuntimeException("Test error message");

        ResponseEntity<ErrorResponse> response = handler.handleException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test error message", response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getCode());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void handleExceptionWithEmptyMessage() {
        RuntimeException exception = new RuntimeException("");

        ResponseEntity<ErrorResponse> response = handler.handleException(exception);

        assertEquals("", response.getBody().getMessage());
    }
}
