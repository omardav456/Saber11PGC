package com.saber11.exam.infraestructure.driver_adapters.jpa_repository;

import com.saber11.exam.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimulacroDataJpaRepository extends JpaRepository<SimulacroData, Long> {
    Optional<SimulacroData> findByCategoria(Categoria categoria);

}
