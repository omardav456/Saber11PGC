package com.saber11.exam.domain.usecase;

import com.saber11.exam.domain.model.Area;
import com.saber11.exam.domain.model.Categoria;
import com.saber11.exam.domain.model.Question;
import com.saber11.exam.domain.model.Simulacro;
import com.saber11.exam.domain.model.gateway.QuestionGateway;
import com.saber11.exam.domain.model.gateway.SimulacroGateway;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SimulacroUseCase {
    private final SimulacroGateway simulacroGateway;
    private final QuestionGateway questionGateway;

    public Simulacro createSimulacro(Simulacro simulacro) {
        return simulacroGateway.createSimulacro(simulacro);

    }
    public Simulacro createSimulacroAuto() {
        List<Question> questions= questionGateway.getQuestions();
        List<Question> selectedQuestions =
                new ArrayList<>();

        for (Area area : Area.values()) {

            List<Question> questionsByArea =
                    questions.stream()
                            .filter(q -> q.getArea() == area)
                            .collect(Collectors.toList());

            Collections.shuffle(questionsByArea);

            selectedQuestions.addAll(
                    questionsByArea.stream()
                            .limit(50)
                            .toList()
            );
        }

        Collections.shuffle(selectedQuestions);
        return simulacroGateway.createSimulacroAuto(selectedQuestions);

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
