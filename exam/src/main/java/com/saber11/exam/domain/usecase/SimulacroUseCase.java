package com.saber11.exam.domain.usecase;

import com.saber11.exam.domain.model.Area;
import com.saber11.exam.domain.model.Categoria;
import com.saber11.exam.domain.model.Simulacro;
import com.saber11.exam.domain.model.gateway.SimulacroGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SimulacroUseCase {
    private final SimulacroGateway simulacroGateway;

    public Simulacro createSimulacro(Simulacro simulacro) {
        return simulacroGateway.createSimulacro(simulacro);

    }
    public List<Simulacro> getSimulacroByCategoria(Categoria categoria) {
        return simulacroGateway.getSimulacroByCategoria(categoria);
    }

    public void deleteSimulacro(Long id){
        simulacroGateway.deleteSimulacro(id);
    }
    public Simulacro getSimulacroById(Long id){
        return simulacroGateway.getSimulacroById(id);
    }

    public List<Simulacro> getAllSimulacro(){
        return simulacroGateway.getAllSimulacro();
    }

}
