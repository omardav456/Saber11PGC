package com.saber11.auth.domain.usecase;

import com.saber11.auth.domain.model.Rol;
import com.saber11.auth.domain.model.User;
import com.saber11.auth.domain.model.gateway.EncryptGateway;
import com.saber11.auth.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserGateway userGateway;
    private final EncryptGateway encryptGateway;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Transactional
    public User saveUser(User user, String adminCedula) {

        if (user.getCedula() == null || user.getCedula().isBlank()) {
            throw new RuntimeException("La cédula es obligatoria");
        }
        if (user.getNombre() == null || user.getNombre().isBlank() || user.getNombre().length() > 20) {
            throw new RuntimeException("El campo nombre no puede estar vacío y debe tener máximo 20 caracteres");
        }
        if (user.getCorreo() == null || user.getCorreo().isBlank()) {
            throw new RuntimeException("El correo es obligatorio");
        }

        user.setCorreo(user.getCorreo().toLowerCase());

        if (!EMAIL_PATTERN.matcher(user.getCorreo()).matches()) {
            throw new RuntimeException("El formato del correo no es válido");
        }

        if (userGateway.searchByEmail(user.getCorreo()) != null) {
            throw new RuntimeException("El correo ya está registrado");
        }

        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new RuntimeException("La contraseña debe tener mínimo 8 caracteres");
        }
        if (user.getRol() == null) {
            throw new RuntimeException("El rol es obligatorio");
        }

        validateRoleAssignment(user.getRol(), adminCedula);

        if (userGateway.searchUserCC(user.getCedula()) != null) {
            throw new RuntimeException("El usuario ya existe");
        }

        String passEncrypter = encryptGateway.encrypt(user.getPassword());
        user.setPassword(passEncrypter);

        return userGateway.saveUser(user);
    }

    @Transactional
    public User actuUser(User user, String adminCedula){
        if(user.getCedula() == null){
            throw new RuntimeException("La cédula es obligatoria");
        }

        User existente = userGateway.searchUserCC(user.getCedula());

        if(existente == null){
            throw new RuntimeException("Usuario no existe");
        }

        if (user.getNombre() == null || user.getNombre().isBlank() || user.getNombre().length() > 20) {
            throw new RuntimeException("El campo nombre no puede estar vacío y debe tener máximo 20 caracteres");
        }

        if (user.getCorreo() != null && !user.getCorreo().isBlank()) {
            user.setCorreo(user.getCorreo().toLowerCase());
            if (!EMAIL_PATTERN.matcher(user.getCorreo()).matches()) {
                throw new RuntimeException("El formato del correo no es válido");
            }
            User userWithEmail = userGateway.searchByEmail(user.getCorreo());
            if (userWithEmail != null && !userWithEmail.getCedula().equals(user.getCedula())) {
                throw new RuntimeException("El correo ya está registrado por otro usuario");
            }
        }

        if (user.getRol() != null) {
            validateRoleAssignment(user.getRol(), adminCedula);
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            user.setPassword(existente.getPassword());
        } else {
            if (user.getPassword().length() < 8) {
                throw new RuntimeException("La contraseña debe tener mínimo 8 caracteres");
            }
            user.setPassword(encryptGateway.encrypt(user.getPassword()));
        }

        if (user.getRol() == null) {
            user.setRol(existente.getRol());
        }

        return userGateway.actuUser(user);
    }

    @Transactional(readOnly = true)
    public User searchUserCC(String cedula){
        User usuario = userGateway.searchUserCC(cedula);

        if(usuario == null){
            throw new RuntimeException("Usuario no encontrado");
        }

        return usuario;
    }

    @Transactional
    public void deleteUserCC(String cedula){
        User user = userGateway.searchUserCC(cedula);

        if(user == null){
            throw new RuntimeException("Usuario no existe");
        }

        userGateway.deleteUserCC(cedula);
    }

    @Transactional(readOnly = true)
    public String login(String correo, String password){
        if(correo == null || password == null){
            throw new RuntimeException("Correo y contraseña obligatorios");
        }
        User user = userGateway.login(correo.toLowerCase());
        if(user == null){
            throw new RuntimeException("Usuario no encontrado");
        }
        if(!encryptGateway.matches(password, user.getPassword())){
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user.getRol();
    }

    private void validateRoleAssignment(Rol targetRol, String adminCedula) {
        if (!userGateway.existsByRol(Rol.ADMINISTRADOR)) {
            return;
        }

        if (adminCedula == null || adminCedula.isBlank()) {
            throw new RuntimeException("Se requiere un administrador para asignar este rol");
        }

        User admin = userGateway.searchUserCC(adminCedula);
        if (admin == null) {
            throw new RuntimeException("Administrador no encontrado");
        }
        if (admin.getRol() != Rol.ADMINISTRADOR) {
            throw new RuntimeException("Solo un administrador puede asignar este rol");
        }
    }

}
