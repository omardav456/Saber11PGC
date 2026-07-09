package com.saber11.auth.domain.model.gateway;

import com.saber11.auth.domain.model.Rol;
import com.saber11.auth.domain.model.User;

import java.util.List;

public interface UserGateway {
    User saveUser(User user);
    User actuUser(User user);
    User searchUserCC(String cedula);
    User searchByEmail(String correo);
    void deleteUserCC(String cedula);
    User login(String correo);
    boolean existsByRol(Rol rol);
    List<User> findAll();
}
