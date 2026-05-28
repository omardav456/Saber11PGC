package com.saber11.auth.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

}