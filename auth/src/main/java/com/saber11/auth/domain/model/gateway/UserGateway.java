package com.saber11.auth.domain.model.gateway;

import com.saber11.auth.domain.model.Rol;
import com.saber11.auth.domain.model.User;

public interface UserGateway {
    User saveUser(User user);
    User actuUser(User user);
    User searchUserCC(String cedula);
    User searchByEmail(String correo);
    void deleteUserCC(String cedula);
    User login(String correo);
    boolean existsByRol(Rol rol);
}
