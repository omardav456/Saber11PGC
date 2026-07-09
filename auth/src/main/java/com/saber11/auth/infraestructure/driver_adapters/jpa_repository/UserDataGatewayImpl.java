package com.saber11.auth.infraestructure.driver_adapters.jpa_repository;


import com.saber11.auth.domain.model.Rol;
import com.saber11.auth.domain.model.User;
import com.saber11.auth.domain.model.gateway.UserGateway;
import com.saber11.auth.infraestructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserDataGatewayImpl implements UserGateway {

    private final UserDataJpaRepository userDataJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User saveUser(User user){
        UserData userDataSave = userMapper.toUserData(user);
        return userMapper.toUser(userDataJpaRepository.save(userDataSave));
    }

    @Override
    public User actuUser(User user){
        if(!userDataJpaRepository.existsById(user.getCedula())){
            throw new RuntimeException("Usuario no existe");
        }
        UserData userData = userMapper.toUserData(user);
        return userMapper.toUser(userDataJpaRepository.save(userData));
    }

    @Override
    public User searchUserCC(String cedula){
        UserData userData = userDataJpaRepository.findById(cedula).orElse(null);

        if (userData == null) {
            return null;
        }

        return userMapper.toUser(userData);
    }

    @Override
    public User searchByEmail(String correo){
        UserData userData = userDataJpaRepository.findByCorreo(correo).orElse(null);
        if (userData == null) {
            return null;
        }
        return userMapper.toUser(userData);
    }

    @Override
    public void deleteUserCC(String cedula) {
        userDataJpaRepository.deleteById(cedula);
    }



    @Override
    public User login(String correo){
        UserData userData = userDataJpaRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return userMapper.toUser(userData);
    }
    @Override
    public List<User> findAll(){
        return userDataJpaRepository.findAll().stream()
                .map(userMapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByRol(Rol rol){
        return userDataJpaRepository.existsByRol(rol);
    }






}
