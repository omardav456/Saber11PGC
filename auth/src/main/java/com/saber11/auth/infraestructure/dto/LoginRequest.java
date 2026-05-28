package com.saber11.auth.infraestructure.dto;

import lombok.Data;

@Data
public class LoginRequest {
    String correo;
    String password;
}
