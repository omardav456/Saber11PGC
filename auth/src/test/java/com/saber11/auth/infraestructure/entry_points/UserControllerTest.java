package com.saber11.auth.infraestructure.entry_points;

import com.saber11.auth.domain.model.Rol;
import com.saber11.auth.domain.model.User;
import com.saber11.auth.domain.usecase.UserUseCase;
import com.saber11.auth.infraestructure.driver_adapters.jpa_repository.UserData;
import com.saber11.auth.infraestructure.dto.LoginRequest;
import com.saber11.auth.infraestructure.dto.UserResponse;
import com.saber11.auth.infraestructure.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserUseCase userUseCase;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @Test
    void saveUser_ReturnsOk() {
        UserData userData = new UserData("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        User savedUser = new User("123", "nombre", "correo@test.com", "encrypted", Rol.DOCENTE);
        UserResponse userResponse = new UserResponse("nombre", "correo@test.com", Rol.DOCENTE);
        when(userMapper.toUser(userData)).thenReturn(user);
        when(userUseCase.saveUser(user, "admin1")).thenReturn(savedUser);
        when(userMapper.toUserResponse(savedUser)).thenReturn(userResponse);
        ResponseEntity<UserResponse> response = userController.saveUser(userData, "admin1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("nombre", response.getBody().getNombre());
    }

    @Test
    void saveUser_ReturnsConflict_WhenCedulaIsNull() {
        UserData userData = new UserData("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        User savedUser = new User(null, "nombre", "correo@test.com", "encrypted", Rol.DOCENTE);
        when(userMapper.toUser(userData)).thenReturn(user);
        when(userUseCase.saveUser(user, null)).thenReturn(savedUser);
        ResponseEntity<UserResponse> response = userController.saveUser(userData, null);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void actuUser_ReturnsOk() {
        UserData userData = new UserData("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        when(userMapper.toUser(userData)).thenReturn(user);
        when(userUseCase.actuUser(user, null)).thenReturn(user);
        ResponseEntity<User> response = userController.actuUser(userData, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("123", response.getBody().getCedula());
    }

    @Test
    void searchUser_ReturnsOk() {
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        UserResponse userResponse = new UserResponse("nombre", "correo@test.com", Rol.DOCENTE);
        when(userUseCase.searchUserCC("123")).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);
        ResponseEntity<UserResponse> response = userController.searchUser("123");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("correo@test.com", response.getBody().getCorreo());
    }

    @Test
    void searchUser_ReturnsNotFound_WhenCedulaIsNull() {
        User user = new User(null, "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        when(userUseCase.searchUserCC("123")).thenReturn(user);
        ResponseEntity<UserResponse> response = userController.searchUser("123");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void searchUser_ReturnsNotFound_WhenUserFoundIsNull() {
        when(userUseCase.searchUserCC("999")).thenReturn(null);
        ResponseEntity<UserResponse> response = userController.searchUser("999");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteUser_ReturnsNoContent() {
        ResponseEntity<Void> response = userController.deleteUser("123");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(userUseCase).deleteUserCC("123");
    }

    @Test
    void login_ReturnsOk() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCorreo("correo@test.com");
        loginRequest.setPassword("password");
        when(userUseCase.login("correo@test.com", "password")).thenReturn("Bienvenu");
        ResponseEntity<String> response = userController.login(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bienvenu", response.getBody());
    }
}
