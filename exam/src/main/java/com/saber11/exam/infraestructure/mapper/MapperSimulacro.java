package com.saber11.exam.infraestructure.mapper;

import com.saber11.exam.domain.model.Simulacro;
import com.saber11.exam.infraestructure.driver_adapters.jpa_repository.SimulacroData;
import org.springframework.stereotype.Component;

@Component
public class MapperSimulacro {

    public Simulacro toSimulacro(SimulacroData simulacroData) {
        Simulacro simulacro = new Simulacro(
                simulacroData.getId(),
                simulacroData.getCategoria(),
                simulacroData.getTiempoLimite(),
                simulacroData.getQuestionsId()
        );
        return simulacro;
    }
    public SimulacroData toSimulacroData(Simulacro simulacro) {
        SimulacroData simulacroData = new SimulacroData(
                simulacro.getId(),
                simulacro.getCategoria(),
                simulacro.getTiempoLimite(),
                simulacro.getQuestionIds()
        );
        return simulacroData;
    }
}
