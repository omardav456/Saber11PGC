package com.saber11.auth.infraestructure.dto;

import com.saber11.auth.domain.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileResponse {
    private String cedula;
    private String nombre;
    private String correo;
    private Rol rol;
}
