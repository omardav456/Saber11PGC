package com.saber11.exam.infraestructure.driver_adapters.jpa_repository;

import com.saber11.exam.domain.model.Area;
import com.saber11.exam.domain.model.Categoria;
import com.saber11.exam.domain.model.Question;
import com.saber11.exam.domain.model.Simulacro;
import com.saber11.exam.domain.model.gateway.SimulacroGateway;
import com.saber11.exam.infraestructure.mapper.MapperSimulacro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SimulacroDataGatewayImp implements SimulacroGateway {
    private final SimulacroDataJpaRepository simulacroDataJpaRepository;
    private final MapperSimulacro mapperSimulacro;


    @Override
    public Simulacro createSimulacro(Simulacro sumulacro) {
        SimulacroData simulacroData = mapperSimulacro.toSimulacroData(sumulacro);
        Simulacro simulacro= mapperSimulacro.toSimulacro(simulacroDataJpaRepository.save(simulacroData));
        return simulacro;
    }

    @Override
    public Simulacro createSimulacroAuto(List<Question> questions) {
        SimulacroData simulacroData= new SimulacroData();
        simulacroData.setCategoria(Categoria.REAL);
        simulacroData.setQuestions(questions);
        simulacroDataJpaRepository.save(simulacroData);
        return mapperSimulacro.toSimulacro(simulacroData);
    }

    @Override
    public List<Simulacro> getSimulacroByCategoria(Categoria categoria) {
        List<SimulacroData> simulacrosData = simulacroDataJpaRepository.findByCategoria(categoria).orElseThrow(() -> new RuntimeException("No existe"));
        List<Simulacro> simulacros= simulacrosData.stream().map(simulacroData -> mapperSimulacro.toSimulacro(simulacroData)).toList();
        return simulacros;
    }

    @Override
    public void deleteSimulacro(Long id) {
        simulacroDataJpaRepository.deleteById(id);
    }

    @Override
    public Simulacro getSimulacroById(Long id) {
        SimulacroData simulacroData= simulacroDataJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe"));
        return mapperSimulacro.toSimulacro(simulacroData);
    }

    @Override
    public List<Simulacro> getAllSimulacro() {
        List<SimulacroData> list = simulacroDataJpaRepository.findAll();
        List<Simulacro> simulacros = list.stream().map(simulacroData -> mapperSimulacro.toSimulacro(simulacroData)).toList();
        return simulacros;
    }
}
