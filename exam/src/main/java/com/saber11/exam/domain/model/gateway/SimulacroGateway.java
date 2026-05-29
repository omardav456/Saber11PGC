package com.saber11.exam.domain.model.gateway;

import com.saber11.exam.domain.model.Area;
import com.saber11.exam.domain.model.Simulacro;

import java.util.List;

public interface SimulacroGateway {
    Simulacro createSimulacro(Simulacro sumulacro);
    Simulacro getSimulacroByCategoria(Area area);
    void deleteSimulacro(Long id);
    Simulacro getSimulacroById(Long id);
    List<Simulacro> getAllSimulacro();
}
