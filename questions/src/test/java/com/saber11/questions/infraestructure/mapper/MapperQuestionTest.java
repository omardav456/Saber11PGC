package com.saber11.questions.infraestructure.mapper;

import com.saber11.questions.domain.model.AnswerOption;
import com.saber11.questions.domain.model.Area;
import com.saber11.questions.domain.model.Question;
import com.saber11.questions.infraestructure.driver_adapters.jpa_repository.QuestionData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperQuestionTest {

    private final MapperQuestion mapper = new MapperQuestion();

    @Test
    void toQuestion_MapsAllFields() {
        QuestionData data = new QuestionData(
                1L,
                Area.MATEMATICAS,
                "¿Cuanto es 2+2?",
                AnswerOption.A,
                "4",
                "3",
                "5",
                "6",
                "Porque 2+2=4"
        );

        Question question = mapper.toQuestion(data);

        assertNotNull(question);
        assertEquals(1L, question.getId());
        assertEquals(Area.MATEMATICAS, question.getArea());
        assertEquals("¿Cuanto es 2+2?", question.getQuestion());
        assertEquals(AnswerOption.A, question.getAnswerOption());
        assertEquals("4", question.getOptionA());
        assertEquals("3", question.getOptionB());
        assertEquals("5", question.getOptionC());
        assertEquals("6", question.getOptionD());
        assertEquals("Porque 2+2=4", question.getJustification());
    }

    @Test
    void toQuestionData_MapsAllFields() {
        Question question = new Question(
                2L,
                Area.INGLES,
                "What is your name?",
                AnswerOption.B,
                "My name is",
                "Your name is",
                "His name is",
                "Her name is",
                "Grammar rule"
        );

        QuestionData data = mapper.toQuestionData(question);

        assertNotNull(data);
        assertEquals(2L, data.getId());
        assertEquals(Area.INGLES, data.getArea());
        assertEquals("What is your name?", data.getQuestion());
        assertEquals(AnswerOption.B, data.getAnswerOption());
        assertEquals("My name is", data.getOptionA());
        assertEquals("Your name is", data.getOptionB());
        assertEquals("His name is", data.getOptionC());
        assertEquals("Her name is", data.getOptionD());
        assertEquals("Grammar rule", data.getJustification());
    }
}
