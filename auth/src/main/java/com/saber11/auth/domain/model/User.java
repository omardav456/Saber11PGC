package com.saber11.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private String cedula;
    private String nombre;
    private String correo;
    private String password;
    private Rol rol;

}
