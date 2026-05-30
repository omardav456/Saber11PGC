package com.saber11.auth.infraestructure.mapper;

import com.saber11.auth.domain.model.Rol;
import com.saber11.auth.domain.model.User;
import com.saber11.auth.infraestructure.driver_adapters.jpa_repository.UserData;
import com.saber11.auth.infraestructure.dto.UserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    void toUserData_MapsAllFields() {
        User user = new User("123", "nombre", "correo@test.com", "password", Rol.DOCENTE);
        UserData userData = userMapper.toUserData(user);
        assertEquals("123", userData.getCedula());
        assertEquals("nombre", userData.getNombre());
        assertEquals("correo@test.com", userData.getCorreo());
        assertEquals("password", userData.getPassword());
        assertEquals(Rol.DOCENTE, userData.getRol());
    }

    @Test
    void toUser_MapsAllFields() {
        UserData userData = new UserData("123", "nombre", "correo@test.com", "password", Rol.ADMINISTRADOR);
        User user = userMapper.toUser(userData);
        assertEquals("123", user.getCedula());
        assertEquals("nombre", user.getNombre());
        assertEquals("correo@test.com", user.getCorreo());
        assertEquals("password", user.getPassword());
        assertEquals(Rol.ADMINISTRADOR, user.getRol());
    }

    @Test
    void toUserResponse_MapsAllFields() {
        User user = new User("123", "nombre", "correo@test.com", "password", Rol.DOCENTE);
        UserResponse response = userMapper.toUserResponse(user);
        assertEquals("nombre", response.getNombre());
        assertEquals("correo@test.com", response.getCorreo());
        assertEquals(Rol.DOCENTE, response.getRol());
    }
}
