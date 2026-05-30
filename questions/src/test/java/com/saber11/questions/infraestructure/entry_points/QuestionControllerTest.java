package com.saber11.questions.infraestructure.entry_points;

import com.saber11.questions.domain.model.Area;
import com.saber11.questions.domain.model.Question;
import com.saber11.questions.domain.usecase.QuestionUseCase;
import com.saber11.questions.infraestructure.driver_adapters.jpa_repository.QuestionData;
import com.saber11.questions.infraestructure.mapper.MapperQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

    @Mock
    private QuestionUseCase questionUseCase;

    @Mock
    private MapperQuestion mapperQuestion;

    @InjectMocks
    private QuestionController questionController;

    @Test
    void createQuestion_ReturnsOk() {
        QuestionData questionData = new QuestionData();
        Question question = new Question();
        question.setId(1L);

        when(mapperQuestion.toQuestion(questionData)).thenReturn(question);
        when(questionUseCase.createQuestion(question)).thenReturn(question);

        ResponseEntity<Question> response = questionController.createQuestion(questionData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(mapperQuestion).toQuestion(questionData);
        verify(questionUseCase).createQuestion(question);
    }

    @Test
    void findQuestionById_ReturnsOk() {
        Long id = 1L;
        Question question = new Question();
        question.setId(id);

        when(questionUseCase.findQuestionById(id)).thenReturn(question);

        ResponseEntity<Question> response = questionController.findQuestionById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    void findQuestionsByArea_ReturnsOk() {
        Area area = Area.MATEMATICAS;
        Question question = new Question();
        List<Question> questions = List.of(question);

        when(questionUseCase.findQuestionsByArea(area)).thenReturn(questions);

        ResponseEntity<List<Question>> response = questionController.findQuestionsByArea(area);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void findAllQuestions_ReturnsOk() {
        Question question = new Question();
        List<Question> questions = List.of(question);

        when(questionUseCase.findAllQuestions()).thenReturn(questions);

        ResponseEntity<List<Question>> response = questionController.findAllQuestions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void updateQuestion_ReturnsOk() {
        Question question = new Question();
        question.setId(1L);

        when(questionUseCase.updateQuestion(question)).thenReturn(question);

        ResponseEntity<Question> response = questionController.updateQuestion(question);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void deleteQuestionById_ReturnsOk() {
        Long id = 1L;

        ResponseEntity response = questionController.deleteQuestionById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(questionUseCase).deleteQuestionById(id);
    }
}
