package com.saber11.questions.infraestructure.mapper;

import com.saber11.questions.domain.model.Question;
import com.saber11.questions.infraestructure.driver_adapters.jpa_repository.QuestionData;
import org.springframework.stereotype.Component;

@Component
public class MapperQuestion {
    public Question toQuestion(QuestionData questionData) {
        Question question = new Question(
                questionData.getId(),
                questionData.getArea(),
                questionData.getQuestion(),
                questionData.getAnswer(),
                questionData.getOptionA(),
                questionData.getOptionB(),
                questionData.getOptionC(),
                questionData.getOptionD(),
                questionData.getJustification()
        );
        return question;
    }
    public QuestionData toQuestionData(Question question) {
        QuestionData questionData = new QuestionData(
                question.getId(),
                question.getArea(),
                question.getQuestion(),
                question.getAnswer(),
                question.getOptionA(),
                question.getOptionB(),
                question.getOptionC(),
                question.getOptionD(),
                question.getJustification()
        );
        return questionData;
    }
}
