package com.saber11.questions.domain.usecase;

import com.saber11.questions.domain.model.Area;
import com.saber11.questions.domain.model.Question;
import com.saber11.questions.domain.model.gateway.QuestionGateway;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
public class QuestionUseCase {

    QuestionGateway questionGateway;


    public Question createQuestion(Question question){
        return questionGateway.createQuestion(question);
    }

    public Question findQuestionById(Long id){
        return questionGateway.findQuestionById(id);
    }

    public List<Question> findQuestionsByArea(Area area){
        return questionGateway.findQuestionsByArea(area);
    }

    public List<Question> findAllQuestions(){
        return questionGateway.findAllQuestions();
    }

    public Question updateQuestion(Question question){
        return questionGateway.updateQuestion(question);
    }
    public void deleteQuestionById(Long id){
        questionGateway.deleteQuestionById(id);
    }
}
