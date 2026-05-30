package com.saber11.auth.domain.usecase;

import com.saber11.auth.domain.model.Rol;
import com.saber11.auth.domain.model.User;
import com.saber11.auth.domain.model.gateway.EncryptGateway;
import com.saber11.auth.domain.model.gateway.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @Mock
    private EncryptGateway encryptGateway;

    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    void saveUser_ThrowsException_WhenCedulaIsNull() {
        User user = new User(null, "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("La cédula es obligatoria", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenCedulaIsBlank() {
        User user = new User("", "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("La cédula es obligatoria", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenNombreIsNull() {
        User user = new User("123", null, "correo@test.com", "password123", Rol.DOCENTE);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("El campo nombre no puede estar vacío y debe tener máximo 20 caracteres", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenNombreIsBlank() {
        User user = new User("123", "", "correo@test.com", "password123", Rol.DOCENTE);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("El campo nombre no puede estar vacío y debe tener máximo 20 caracteres", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenNombreExceedsMaxLength() {
        User user = new User("123", "nombre demasiado largo", "correo@test.com", "password123", Rol.DOCENTE);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("El campo nombre no puede estar vacío y debe tener máximo 20 caracteres", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenCorreoIsNull() {
        User user = new User("123", "nombre", null, "password123", Rol.DOCENTE);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("El correo es obligatorio", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenCorreoIsBlank() {
        User user = new User("123", "nombre", "", "password123", Rol.DOCENTE);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("El correo es obligatorio", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenCorreoFormatInvalid() {
        User user = new User("123", "nombre", "correo-invalido", "password123", Rol.DOCENTE);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("El formato del correo no es válido", ex.getMessage());
    }

    @Test
    void saveUser_ConvertsCorreoToLowerCase_BeforeValidation() {
        User user = new User("123", "nombre", "CORREO@TEST.com", "password123", Rol.DOCENTE);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(false);
        when(encryptGateway.encrypt("password123")).thenReturn("encrypted");
        when(userGateway.saveUser(any(User.class))).thenAnswer(i -> i.getArgument(0));
        User result = userUseCase.saveUser(user, null);
        assertEquals("correo@test.com", result.getCorreo());
    }

    @Test
    void saveUser_ThrowsException_WhenCorreoAlreadyRegistered() {
        User user = new User("123", "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(new User("999", "otro", "correo@test.com", "pass", Rol.DOCENTE));
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("El correo ya está registrado", ex.getMessage());
        verify(userGateway).searchByEmail("correo@test.com");
    }

    @Test
    void saveUser_ThrowsException_WhenPasswordIsNull() {
        User user = new User("123", "nombre", "correo@test.com", null, Rol.DOCENTE);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("La contraseña debe tener mínimo 8 caracteres", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenPasswordTooShort() {
        User user = new User("123", "nombre", "correo@test.com", "1234567", Rol.DOCENTE);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("La contraseña debe tener mínimo 8 caracteres", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenRolIsNull() {
        User user = new User("123", "nombre", "correo@test.com", "password123", null);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("El rol es obligatorio", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenAdminCedulaNullAndAdminExists() {
        User user = new User("123", "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(true);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, null));
        assertEquals("Se requiere un administrador para asignar este rol", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenAdminCedulaIsBlank() {
        User user = new User("123", "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(true);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, ""));
        assertEquals("Se requiere un administrador para asignar este rol", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenAdminNotFound() {
        User user = new User("123", "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(true);
        when(userGateway.searchUserCC("admin123")).thenReturn(null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, "admin123"));
        assertEquals("Administrador no encontrado", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenAdminHasWrongRole() {
        User user = new User("123", "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        User admin = new User("admin123", "admin", "admin@test.com", "pass", Rol.DOCENTE);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(true);
        when(userGateway.searchUserCC("admin123")).thenReturn(admin);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, "admin123"));
        assertEquals("Solo un administrador puede asignar este rol", ex.getMessage());
    }

    @Test
    void saveUser_ThrowsException_WhenUserAlreadyExists() {
        User user = new User("123", "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        User admin = new User("admin123", "admin", "admin@test.com", "pass", Rol.ADMINISTRADOR);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(true);
        when(userGateway.searchUserCC("admin123")).thenReturn(admin);
        when(userGateway.searchUserCC("123")).thenReturn(new User("123", "existente", "existe@test.com", "pass", Rol.DOCENTE));
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.saveUser(user, "admin123"));
        assertEquals("El usuario ya existe", ex.getMessage());
    }

    @Test
    void saveUser_Success_WhenNoAdminExistsForDocente() {
        User user = new User("123", "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        User savedUser = new User("123", "nombre", "correo@test.com", "encryptedPass", Rol.DOCENTE);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(false);
        when(encryptGateway.encrypt("password123")).thenReturn("encryptedPass");
        when(userGateway.saveUser(any(User.class))).thenReturn(savedUser);
        User result = userUseCase.saveUser(user, null);
        assertNotNull(result);
        assertEquals("encryptedPass", result.getPassword());
        verify(userGateway).saveUser(any(User.class));
        verify(encryptGateway).encrypt("password123");
    }

    @Test
    void saveUser_Success_WhenValidAdminAssignsDocente() {
        User user = new User("123", "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        User admin = new User("admin123", "admin", "admin@test.com", "pass", Rol.ADMINISTRADOR);
        User savedUser = new User("123", "nombre", "correo@test.com", "encryptedPass", Rol.DOCENTE);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(true);
        when(userGateway.searchUserCC("admin123")).thenReturn(admin);
        when(encryptGateway.encrypt("password123")).thenReturn("encryptedPass");
        when(userGateway.saveUser(any(User.class))).thenReturn(savedUser);
        User result = userUseCase.saveUser(user, "admin123");
        assertNotNull(result);
        assertEquals("correo@test.com", result.getCorreo());
        verify(userGateway).saveUser(any(User.class));
    }

    @Test
    void saveUser_Success_WhenValidAdminAssignsAdministrador() {
        User user = new User("123", "nombre", "correo@test.com", "password123", Rol.ADMINISTRADOR);
        User admin = new User("admin123", "admin", "admin@test.com", "pass", Rol.ADMINISTRADOR);
        User savedUser = new User("123", "nombre", "correo@test.com", "encryptedPass", Rol.ADMINISTRADOR);
        when(userGateway.searchByEmail("correo@test.com")).thenReturn(null);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(true);
        when(userGateway.searchUserCC("admin123")).thenReturn(admin);
        when(encryptGateway.encrypt("password123")).thenReturn("encryptedPass");
        when(userGateway.saveUser(any(User.class))).thenReturn(savedUser);
        User result = userUseCase.saveUser(user, "admin123");
        assertNotNull(result);
        assertEquals(Rol.ADMINISTRADOR, result.getRol());
        verify(userGateway).saveUser(any(User.class));
    }

    @Test
    void actuUser_ThrowsException_WhenCedulaIsNull() {
        User user = new User(null, "nombre", "correo@test.com", "password123", Rol.DOCENTE);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.actuUser(user, null));
        assertEquals("La cédula es obligatoria", ex.getMessage());
    }

    @Test
    void actuUser_ThrowsException_WhenUserNotFound() {
        User user = new User("123", "nombre", null, "password123", Rol.DOCENTE);
        when(userGateway.searchUserCC("123")).thenReturn(null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.actuUser(user, null));
        assertEquals("Usuario no existe", ex.getMessage());
    }

    @Test
    void actuUser_ThrowsException_WhenNombreIsNull() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", null, null, null, null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.actuUser(user, null));
        assertEquals("El campo nombre no puede estar vacío y debe tener máximo 20 caracteres", ex.getMessage());
    }

    @Test
    void actuUser_ThrowsException_WhenNombreIsBlank() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "", null, null, null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.actuUser(user, null));
        assertEquals("El campo nombre no puede estar vacío y debe tener máximo 20 caracteres", ex.getMessage());
    }

    @Test
    void actuUser_ThrowsException_WhenNombreExceedsMaxLength() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nombre demasiado largo", null, null, null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.actuUser(user, null));
        assertEquals("El campo nombre no puede estar vacío y debe tener máximo 20 caracteres", ex.getMessage());
    }

    @Test
    void actuUser_ThrowsException_WhenCorreoFormatInvalid() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nombre", "correo-invalido", null, null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.actuUser(user, null));
        assertEquals("El formato del correo no es válido", ex.getMessage());
    }

    @Test
    void actuUser_ThrowsException_WhenCorreoAlreadyTakenByOtherUser() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nombre", "nuevo@test.com", null, null);
        User otherUser = new User("456", "otro", "nuevo@test.com", "pass", Rol.DOCENTE);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        when(userGateway.searchByEmail("nuevo@test.com")).thenReturn(otherUser);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.actuUser(user, null));
        assertEquals("El correo ya está registrado por otro usuario", ex.getMessage());
    }

    @Test
    void actuUser_Success_WithOwnEmail_DoesNotThrow() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nombre", "old@test.com", null, null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        when(userGateway.searchByEmail("old@test.com")).thenReturn(existing);
        when(userGateway.actuUser(argThat(u -> "oldPass".equals(u.getPassword())))).thenAnswer(i -> i.getArgument(0));
        User result = userUseCase.actuUser(user, null);
        assertNotNull(result);
        verify(userGateway).actuUser(any(User.class));
    }

    @Test
    void actuUser_ThrowsException_WhenPasswordTooShort() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nombre", null, "1234567", null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.actuUser(user, null));
        assertEquals("La contraseña debe tener mínimo 8 caracteres", ex.getMessage());
    }

    @Test
    void actuUser_Success_WithPasswordNull_KeepsOldPassword() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nuevoNombre", null, null, null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        when(userGateway.actuUser(argThat(u -> "oldPass".equals(u.getPassword())))).thenAnswer(i -> i.getArgument(0));
        User result = userUseCase.actuUser(user, null);
        assertEquals("oldPass", result.getPassword());
        assertEquals(Rol.DOCENTE, result.getRol());
        verify(userGateway).actuUser(any(User.class));
    }

    @Test
    void actuUser_Success_WithPasswordBlank_KeepsOldPassword() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nuevoNombre", null, "", null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        when(userGateway.actuUser(argThat(u -> "oldPass".equals(u.getPassword())))).thenAnswer(i -> i.getArgument(0));
        User result = userUseCase.actuUser(user, null);
        assertEquals("oldPass", result.getPassword());
        verify(userGateway).actuUser(any(User.class));
    }

    @Test
    void actuUser_Success_WithRolNull_KeepsOldRol() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nuevoNombre", null, "newPass123", null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        when(encryptGateway.encrypt("newPass123")).thenReturn("encryptedNewPass");
        when(userGateway.actuUser(argThat(u -> Rol.DOCENTE == u.getRol()))).thenAnswer(i -> i.getArgument(0));
        User result = userUseCase.actuUser(user, null);
        assertEquals(Rol.DOCENTE, result.getRol());
        assertEquals("encryptedNewPass", result.getPassword());
        verify(userGateway).actuUser(any(User.class));
    }

    @Test
    void actuUser_Success_WithCorreoNull_SkipsValidation() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nuevoNombre", null, "newPass123", null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        when(encryptGateway.encrypt("newPass123")).thenReturn("encryptedNewPass");
        when(userGateway.actuUser(any(User.class))).thenAnswer(i -> i.getArgument(0));
        User result = userUseCase.actuUser(user, null);
        assertEquals("nuevoNombre", result.getNombre());
        assertNull(result.getCorreo());
        verify(userGateway, never()).searchByEmail(anyString());
    }

    @Test
    void actuUser_Success_WithCorreoBlank_SkipsValidation() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nuevoNombre", "", "newPass123", null);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        when(encryptGateway.encrypt("newPass123")).thenReturn("encryptedNewPass");
        when(userGateway.actuUser(any(User.class))).thenAnswer(i -> i.getArgument(0));
        User result = userUseCase.actuUser(user, null);
        assertEquals("nuevoNombre", result.getNombre());
        assertEquals("", result.getCorreo());
        verify(userGateway, never()).searchByEmail(anyString());
    }

    @Test
    void actuUser_Success_WhenRolProvidedAndNoAdminExists() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nuevoNombre", null, null, Rol.ADMINISTRADOR);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(false);
        when(userGateway.actuUser(argThat(u -> Rol.ADMINISTRADOR == u.getRol()))).thenAnswer(i -> i.getArgument(0));
        User result = userUseCase.actuUser(user, null);
        assertEquals(Rol.ADMINISTRADOR, result.getRol());
        assertEquals("oldPass", result.getPassword());
        verify(userGateway).actuUser(any(User.class));
    }

    @Test
    void actuUser_Success_WithAllFieldsUpdated() {
        User existing = new User("123", "old", "old@test.com", "oldPass", Rol.DOCENTE);
        User user = new User("123", "nuevoNombre", "nuevo@test.com", "newPassword123", Rol.ADMINISTRADOR);
        User admin = new User("admin1", "admin", "admin@test.com", "pass", Rol.ADMINISTRADOR);
        User updatedUser = new User("123", "nuevoNombre", "nuevo@test.com", "encryptedNewPass", Rol.ADMINISTRADOR);
        when(userGateway.searchUserCC("123")).thenReturn(existing);
        when(userGateway.searchByEmail("nuevo@test.com")).thenReturn(null);
        when(userGateway.existsByRol(Rol.ADMINISTRADOR)).thenReturn(true);
        when(userGateway.searchUserCC("admin1")).thenReturn(admin);
        when(encryptGateway.encrypt("newPassword123")).thenReturn("encryptedNewPass");
        when(userGateway.actuUser(any(User.class))).thenReturn(updatedUser);
        User result = userUseCase.actuUser(user, "admin1");
        assertNotNull(result);
        assertEquals("nuevoNombre", result.getNombre());
        assertEquals("nuevo@test.com", result.getCorreo());
        assertEquals("encryptedNewPass", result.getPassword());
        assertEquals(Rol.ADMINISTRADOR, result.getRol());
        verify(userGateway).actuUser(any(User.class));
    }

    @Test
    void searchUserCC_ThrowsException_WhenUserNotFound() {
        when(userGateway.searchUserCC("123")).thenReturn(null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.searchUserCC("123"));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void searchUserCC_Success() {
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        when(userGateway.searchUserCC("123")).thenReturn(user);
        User result = userUseCase.searchUserCC("123");
        assertNotNull(result);
        assertEquals("123", result.getCedula());
    }

    @Test
    void deleteUserCC_ThrowsException_WhenUserNotFound() {
        when(userGateway.searchUserCC("123")).thenReturn(null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.deleteUserCC("123"));
        assertEquals("Usuario no existe", ex.getMessage());
        verify(userGateway, never()).deleteUserCC(anyString());
    }

    @Test
    void deleteUserCC_Success() {
        User user = new User("123", "nombre", "correo@test.com", "pass", Rol.DOCENTE);
        when(userGateway.searchUserCC("123")).thenReturn(user);
        userUseCase.deleteUserCC("123");
        verify(userGateway).deleteUserCC("123");
    }

    @Test
    void login_ThrowsException_WhenCorreoIsNull() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.login(null, "password"));
        assertEquals("Correo y contraseña obligatorios", ex.getMessage());
    }

    @Test
    void login_ThrowsException_WhenPasswordIsNull() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.login("correo@test.com", null));
        assertEquals("Correo y contraseña obligatorios", ex.getMessage());
    }

    @Test
    void login_ThrowsException_WhenUserNotFound() {
        when(userGateway.login("correo@test.com")).thenReturn(null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.login("correo@test.com", "password"));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void login_ConvertsCorreoToLowerCase() {
        User user = new User("123", "nombre", "correo@test.com", "encryptedPass", Rol.DOCENTE);
        when(userGateway.login("correo@test.com")).thenReturn(user);
        when(encryptGateway.matches("password", "encryptedPass")).thenReturn(true);
        String result = userUseCase.login("CORREO@TEST.COM", "password");
        assertEquals("Bienvenu", result);
        verify(userGateway).login("correo@test.com");
    }

    @Test
    void login_ThrowsException_WhenPasswordIncorrect() {
        User user = new User("123", "nombre", "correo@test.com", "encryptedPass", Rol.DOCENTE);
        when(userGateway.login("correo@test.com")).thenReturn(user);
        when(encryptGateway.matches("wrongPassword", "encryptedPass")).thenReturn(false);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userUseCase.login("correo@test.com", "wrongPassword"));
        assertEquals("Contraseña incorrecta", ex.getMessage());
    }

    @Test
    void login_Success() {
        User user = new User("123", "nombre", "correo@test.com", "encryptedPass", Rol.DOCENTE);
        when(userGateway.login("correo@test.com")).thenReturn(user);
        when(encryptGateway.matches("correctPassword", "encryptedPass")).thenReturn(true);
        String result = userUseCase.login("correo@test.com", "correctPassword");
        assertEquals("Bienvenu", result);
    }
}
