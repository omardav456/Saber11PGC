package com.saber11.auth.infraestructure.driver_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataJpaRepository extends JpaRepository<UserData, String> {
    Optional<UserData> findByCorreo(String Correo);
}
