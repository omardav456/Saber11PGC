package com.saber11.exam.domain.model.gateway;

import com.saber11.exam.domain.model.Categoria;
import com.saber11.exam.domain.model.Simulacro;

import java.util.List;

public interface SimulacroGateway {
    Simulacro createSimulacro(Simulacro sumulacro);
    Simulacro createSimulacroAuto(List<Long> questionIds);
    List<Simulacro> getSimulacroByCategoria(Categoria categoria);
    void deleteSimulacro(Long id);
    Simulacro getSimulacroById(Long id);
    List<Simulacro> getAllSimulacro();
}
