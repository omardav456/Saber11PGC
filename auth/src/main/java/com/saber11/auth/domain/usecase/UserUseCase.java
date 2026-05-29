package com.saber11.auth.domain.usecase;


import lombok.RequiredArgsConstructor;

import com.saber11.auth.domain.model.User;
import com.saber11.auth.domain.model.gateway.EncryptGateway;
import com.saber11.auth.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserUseCase {
    private final UserGateway userGateway;
    private final EncryptGateway encryptGateway;

    public User saveUser(User user) {

        if (user.getCedula() == null || user.getCedula().isBlank()) {
            throw new RuntimeException("La cédula es obligatoria");
        }
        if (user.getNombre() == null || user.getNombre().isBlank() || user.getNombre().length() > 20) {
            throw new RuntimeException("El campo no puede estar vacio");
        }
        if (user.getCorreo() == null || user.getCorreo().isBlank()) {
            throw new RuntimeException("El correo es obligatorio");
        }


        user.setCorreo(user.getCorreo().toLowerCase());


        if (user.getPassword() == null || user.getPassword().length() < 4) {
            throw new RuntimeException("La contraseña debe tener mínimo 4 caracteres");
        }
        if (userGateway.searchUserCC(user.getCedula()) != null) {
            throw new RuntimeException("El usuario ya existe");
        }
        String passEncrypter = encryptGateway.encrypt(user.getPassword());
        user.setPassword(passEncrypter);

        user.setCorreo(user.getCorreo().toLowerCase());

        return userGateway.saveUser(user);
    }

    public User actuUser(User user){
        if(user.getCedula() == null){
            throw new RuntimeException("La cédula es obligatoria");
        }

        User existente = userGateway.searchUserCC(user.getCedula());

        if(existente == null){
            throw new RuntimeException("Usuario no existe");
        }

        user.setPassword(encryptGateway.encrypt(user.getPassword()));

        return userGateway.actuUser(user);
    }

    public User searchUserCC(String cedula){
        User usuario = userGateway.searchUserCC(cedula);

        if(usuario == null){
            throw new RuntimeException("Usuario no encontrado");
        }

        return usuario;
    }

    public void deleteUserCC(String cedula){
        User user = userGateway.searchUserCC(cedula);

        if(user == null){
            throw new RuntimeException("Usuario no existe");
        }

        userGateway.deleteUserCC(cedula);
    }


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

        return "Bienvenu";
    }

}
