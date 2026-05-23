package com.saber11.questions.domain.model.gateway;

import com.saber11.questions.domain.model.Area;
import com.saber11.questions.domain.model.Question;

import java.util.List;

public interface QuestionGateway {
    Question createQuestion(Question question);
    Question findQuestionById(Long id);
    List<Question> findQuestionsByArea(Area area);
    List<Question> findAllQuestions();
    Question updateQuestion(Question question);
    void deleteQuestionById(Long id);

}
