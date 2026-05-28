package com.saber11.auth.infraestructure.driver_adapters.jpa_repository;

import com.saber11.auth.domain.model.Rol;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Email;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
@Data

public class UserData {

    @Id
    private String cedula;
    private String nombre;
    @Column(unique = true, nullable = false)
    @Email
    private String correo;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;

}