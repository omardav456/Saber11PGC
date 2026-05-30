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
        if(simulacro==null){
            throw new RuntimeException("simulacro is null");
        } else if (simulacro.getCategoria()==null) {
            throw new RuntimeException("simulacro.categoria is null");
        }else if (simulacro.getQuestionIds()==null) {
            throw new RuntimeException("simulacro.questions is null");
        }else if (simulacro.getQuestionIds().size()==0) {
            throw new RuntimeException("simulacro.questions is empty");
        }
        return simulacroGateway.createSimulacro(simulacro);

    }
    public Simulacro createSimulacroAuto() {
        List<Question> questions = questionGateway.getQuestions();

        List<Long> selectedQuestionIds = new ArrayList<>();

        for (Area area : Area.values()) {

            List<Question> questionsByArea =
                    questions.stream()
                            .filter(q -> q.getArea() == area)
                            .collect(Collectors.toList());

            Collections.shuffle(questionsByArea);

            selectedQuestionIds.addAll(
                    questionsByArea.stream()
                            .limit(50)
                            .map(Question::getId)   // 👈 AQUÍ está el cambio clave
                            .toList()
            );
        }

        Collections.shuffle(selectedQuestionIds);

        return simulacroGateway.createSimulacroAuto(selectedQuestionIds);
    }
    public List<Simulacro> getSimulacroByCategoria(Categoria categoria) {
        if(categoria==null){
            throw new RuntimeException("categoria is null");
        }
        return simulacroGateway.getSimulacroByCategoria(categoria);
    }

    public void deleteSimulacro(Long id){
        if(id==null){
            throw new RuntimeException("id is null");
        }else if(id==0){
            throw new RuntimeException("id cant be 0");
        }
        simulacroGateway.deleteSimulacro(id);
    }
    public Simulacro getSimulacroById(Long id){
        if(id==null){
            throw new RuntimeException("id is null");
        }else if(id==0){
            throw new RuntimeException("id cant be 0");
        }
        return simulacroGateway.getSimulacroById(id);
    }

    public List<Simulacro> getAllSimulacro(){
        return simulacroGateway.getAllSimulacro();
    }

}
