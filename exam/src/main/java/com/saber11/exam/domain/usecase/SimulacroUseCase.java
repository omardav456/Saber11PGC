package com.saber11.exam.domain.usecase;

import com.saber11.exam.domain.model.Area;
import com.saber11.exam.domain.model.Simulacro;
import com.saber11.exam.domain.model.gateway.SimulacroGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SimulacroUseCase {
    private final SimulacroGateway simulacroGateway;

    public Simulacro createSimulacro(){
        return simulacroGateway.createSimulacro();

    }
    public Simulacro getSimulacroByArea(Area area){
        return simulacroGateway.getSimulacroByArea(area);
    }
    public Simulacro updateSimulacro(Simulacro simulacro){
        return simulacroGateway.updateSimulacro(simulacro);
    }
    public void deleteSimulacro(Long id){
        simulacroGateway.deleteSimulacro(id);
    }
    public Simulacro getSimulacroById(Long id){
        return simulacroGateway.getSimulacroById(id);
    }
    public List<Simulacro> getAllSimulacro(Long id){
        return simulacroGateway.getAllSimulacro(id);
    }

}
