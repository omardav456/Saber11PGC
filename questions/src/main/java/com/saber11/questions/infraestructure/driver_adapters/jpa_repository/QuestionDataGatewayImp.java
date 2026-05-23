package com.saber11.questions.infraestructure.driver_adapters.jpa_repository;

import com.saber11.questions.domain.model.Area;
import com.saber11.questions.domain.model.Question;
import com.saber11.questions.domain.model.gateway.QuestionGateway;

import java.util.List;

public class QuestionDataGatewayImp  implements QuestionGateway {

    @Override
    public Question createQuestion(Question question) {
        return null;
    }

    @Override
    public Question findQuestionById(Long id) {
        return null;
    }

    @Override
    public List<Question> findQuestionsByArea(Area area) {
        return List.of();
    }

    @Override
    public List<Question> findAllQuestions() {
        return List.of();
    }

    @Override
    public Question updateQuestion(Question question) {
        return null;
    }

    @Override
    public void deleteQuestionById(Long id) {

    }
}
