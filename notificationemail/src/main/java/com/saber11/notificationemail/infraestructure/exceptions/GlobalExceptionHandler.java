package com.saber11.notificationemail.infraestructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)

    public ResponseEntity<String> manejarError(RuntimeException ex){

        if(ex.getMessage().toLowerCase().contains("obligatorio")){

            return new ResponseEntity<>(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if(ex.getMessage().toLowerCase().contains("enviando correo")){

            return new ResponseEntity<>(
                    ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        if(ex.getMessage().toLowerCase().contains("correo")){

            return new ResponseEntity<>(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if(ex.getMessage().toLowerCase().contains("smtp") ||
                ex.getMessage().toLowerCase().contains("mail server") ||
                ex.getMessage().toLowerCase().contains("authentication failed") ||
                ex.getMessage().toLowerCase().contains("plantilla")){

            return new ResponseEntity<>(
                    "Error con servidor de correo",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}