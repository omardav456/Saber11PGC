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
        if (ex.getMessage().contains("correo")) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("El correo ya está registrado");
        }
        if (ex.getMessage().contains("Could not commit JPA transaction")) {
            return ResponseEntity
                    .badRequest()
                    .body("Correo inválido");
        }
        return new ResponseEntity<>(ex.getMessage().toLowerCase(), HttpStatus.BAD_REQUEST);
    }

}

