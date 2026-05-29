package com.saber11.exam.domain.model.gateway;

import com.saber11.exam.domain.model.Question;

import java.util.List;

public interface QuestionGateway {
    List<Question> getQuestions();
}
