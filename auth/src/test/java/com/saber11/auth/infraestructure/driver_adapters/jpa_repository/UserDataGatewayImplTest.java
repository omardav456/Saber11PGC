package com.saber11.auth.infraestructure.driver_adapters.jpa_repository;

import com.saber11.auth.domain.model.Rol;
import com.saber11.auth.domain.model.User;
import com.saber11.auth.infraestructure.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDataGatewayImplTest {

    @Mock
    private UserDataJpaRepository userDataJpaRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserDataGatewayImpl userDataGateway;

    @Test
    void saveUser_DelegatesToRepository() {
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        UserData userData = new UserData("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        when(userMapper.toUserData(user)).thenReturn(userData);
        when(userDataJpaRepository.save(userData)).thenReturn(userData);
        when(userMapper.toUser(userData)).thenReturn(user);
        User result = userDataGateway.saveUser(user);
        assertNotNull(result);
        assertEquals("123", result.getCedula());
        verify(userDataJpaRepository).save(userData);
    }

    @Test
    void actuUser_WhenUserExists_SavesAndReturns() {
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        UserData userData = new UserData("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        when(userDataJpaRepository.existsById("123")).thenReturn(true);
        when(userMapper.toUserData(user)).thenReturn(userData);
        when(userDataJpaRepository.save(userData)).thenReturn(userData);
        when(userMapper.toUser(userData)).thenReturn(user);
        User result = userDataGateway.actuUser(user);
        assertNotNull(result);
        assertEquals("123", result.getCedula());
        verify(userDataJpaRepository).save(userData);
    }

    @Test
    void actuUser_ThrowsException_WhenUserDoesNotExist() {
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        when(userDataJpaRepository.existsById("123")).thenReturn(false);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userDataGateway.actuUser(user));
        assertEquals("Usuario no existe", ex.getMessage());
        verify(userDataJpaRepository, never()).save(any());
    }

    @Test
    void searchUserCC_ReturnsUser_WhenFound() {
        UserData userData = new UserData("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        when(userDataJpaRepository.findById("123")).thenReturn(Optional.of(userData));
        when(userMapper.toUser(userData)).thenReturn(user);
        User result = userDataGateway.searchUserCC("123");
        assertNotNull(result);
        assertEquals("123", result.getCedula());
    }

    @Test
    void searchUserCC_ReturnsNull_WhenNotFound() {
        when(userDataJpaRepository.findById("123")).thenReturn(Optional.empty());
        User result = userDataGateway.searchUserCC("123");
        assertNull(result);
    }

    @Test
    void searchByEmail_ReturnsUser_WhenFound() {
        UserData userData = new UserData("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        when(userDataJpaRepository.findByCorreo("correo@test.com")).thenReturn(Optional.of(userData));
        when(userMapper.toUser(userData)).thenReturn(user);
        User result = userDataGateway.searchByEmail("correo@test.com");
        assertNotNull(result);
        assertEquals("correo@test.com", result.getCorreo());
    }

    @Test
    void searchByEmail_ReturnsNull_WhenNotFound() {
        when(userDataJpaRepository.findByCorreo("correo@test.com")).thenReturn(Optional.empty());
        User result = userDataGateway.searchByEmail("correo@test.com");
        assertNull(result);
    }

    @Test
    void deleteUserCC_DelegatesToRepository() {
        userDataGateway.deleteUserCC("123");
        verify(userDataJpaRepository).deleteById("123");
    }

    @Test
    void login_ReturnsUser_WhenFound() {
        UserData userData = new UserData("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        when(userDataJpaRepository.findByCorreo("correo@test.com")).thenReturn(Optional.of(userData));
        when(userMapper.toUser(userData)).thenReturn(user);
        User result = userDataGateway.login("correo@test.com");
        assertNotNull(result);
        assertEquals("correo@test.com", result.getCorreo());
    }

    @Test
    void login_ThrowsException_WhenNotFound() {
        when(userDataJpaRepository.findByCorreo("correo@test.com")).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userDataGateway.login("correo@test.com"));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void existsByRol_DelegatesToRepository() {
        when(userDataJpaRepository.existsByRol(Rol.ADMINISTRADOR)).thenReturn(true);
        assertTrue(userDataGateway.existsByRol(Rol.ADMINISTRADOR));
        verify(userDataJpaRepository).existsByRol(Rol.ADMINISTRADOR);
    }
}
