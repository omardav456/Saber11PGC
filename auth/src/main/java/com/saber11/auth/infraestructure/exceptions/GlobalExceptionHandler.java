package com.saber11.auth.infraestructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> manejarError(RuntimeException ex) {

        if(ex.getMessage().toLowerCase().contains("no encontrado")){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        if(ex.getMessage().toLowerCase().contains("incorrecta")){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        if ("El correo ya está registrado".equals(ex.getMessage())
                || "El correo ya está registrado por otro usuario".equals(ex.getMessage())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        }
        if ("El formato del correo no es válido".equals(ex.getMessage())) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
        return new ResponseEntity<>(ex.getMessage().toLowerCase(), HttpStatus.BAD_REQUEST);
    }

}

