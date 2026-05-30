package com.saber11.questions.infraestructure.driver_adapters.jpa_repository;

import com.saber11.questions.domain.model.AnswerOption;
import com.saber11.questions.domain.model.Area;
import com.saber11.questions.domain.model.Question;
import com.saber11.questions.infraestructure.mapper.MapperQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionDataGatewayImpTest {

    @Mock
    private MapperQuestion mapperQuestion;

    @Mock
    private QuestionDataJpaRepository questionDataJpaRepository;

    @InjectMocks
    private QuestionDataGatewayImp gateway;

    @Test
    void createQuestion_Success() {
        Question question = new Question(1L, Area.MATEMATICAS, "pregunta", AnswerOption.A, "A", "B", "C", "D", "just");
        QuestionData data = new QuestionData(1L, Area.MATEMATICAS, "pregunta", AnswerOption.A, "A", "B", "C", "D", "just");
        QuestionData savedData = new QuestionData(1L, Area.MATEMATICAS, "pregunta", AnswerOption.A, "A", "B", "C", "D", "just");

        when(mapperQuestion.toQuestionData(question)).thenReturn(data);
        when(questionDataJpaRepository.save(data)).thenReturn(savedData);
        when(mapperQuestion.toQuestion(savedData)).thenReturn(question);

        Question result = gateway.createQuestion(question);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(Area.MATEMATICAS, result.getArea());
        verify(mapperQuestion).toQuestionData(question);
        verify(questionDataJpaRepository).save(data);
        verify(mapperQuestion).toQuestion(savedData);
    }

    @Test
    void findQuestionById_WhenFound() {
        Long id = 1L;
        QuestionData data = new QuestionData(id, Area.MATEMATICAS, "pregunta", AnswerOption.A, "A", "B", "C", "D", "just");
        Question question = new Question(id, Area.MATEMATICAS, "pregunta", AnswerOption.A, "A", "B", "C", "D", "just");

        when(questionDataJpaRepository.findById(id)).thenReturn(Optional.of(data));
        when(mapperQuestion.toQuestion(data)).thenReturn(question);

        Question result = gateway.findQuestionById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findQuestionById_WhenNotFound() {
        Long id = 999L;

        when(questionDataJpaRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gateway.findQuestionById(id));
        assertEquals("Question id not found", exception.getMessage());
    }

    @Test
    void findQuestionsByArea_WhenFound() {
        Area area = Area.MATEMATICAS;
        QuestionData data = new QuestionData(1L, area, "pregunta", AnswerOption.A, "A", "B", "C", "D", "just");
        Question question = new Question(1L, area, "pregunta", AnswerOption.A, "A", "B", "C", "D", "just");
        List<QuestionData> dataList = List.of(data);

        when(questionDataJpaRepository.findByArea(area)).thenReturn(Optional.of(dataList));
        when(mapperQuestion.toQuestion(data)).thenReturn(question);

        List<Question> result = gateway.findQuestionsByArea(area);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(area, result.get(0).getArea());
    }

    @Test
    void findQuestionsByArea_WhenNotFound() {
        Area area = Area.MATEMATICAS;

        when(questionDataJpaRepository.findByArea(area)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gateway.findQuestionsByArea(area));
        assertEquals("Questions not found", exception.getMessage());
    }

    @Test
    void findAllQuestions_ReturnsList() {
        QuestionData data1 = new QuestionData(1L, Area.MATEMATICAS, "p1", AnswerOption.A, "A", "B", "C", "D", "j1");
        QuestionData data2 = new QuestionData(2L, Area.INGLES, "p2", AnswerOption.B, "A", "B", "C", "D", "j2");
        List<QuestionData> dataList = List.of(data1, data2);

        when(questionDataJpaRepository.findAll()).thenReturn(dataList);
        when(mapperQuestion.toQuestion(data1)).thenReturn(new Question(1L, Area.MATEMATICAS, "p1", AnswerOption.A, "A", "B", "C", "D", "j1"));
        when(mapperQuestion.toQuestion(data2)).thenReturn(new Question(2L, Area.INGLES, "p2", AnswerOption.B, "A", "B", "C", "D", "j2"));

        List<Question> result = gateway.findAllQuestions();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void updateQuestion_ReturnsNull() {
        Question question = new Question(1L, Area.MATEMATICAS, "pregunta", AnswerOption.A, "A", "B", "C", "D", "just");
        QuestionData data = new QuestionData(1L, Area.MATEMATICAS, "pregunta", AnswerOption.A, "A", "B", "C", "D", "just");

        when(questionDataJpaRepository.findById(1L)).thenReturn(Optional.of(data));
        when(mapperQuestion.toQuestionData(question)).thenReturn(data);

        Question result = gateway.updateQuestion(question);

        assertNull(result);
        verify(questionDataJpaRepository).findById(1L);
        verify(questionDataJpaRepository).save(data);
    }

    @Test
    void deleteQuestionById_Deletes() {
        Long id = 1L;

        gateway.deleteQuestionById(id);

        verify(questionDataJpaRepository).deleteById(id);
    }
}
