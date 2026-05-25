package com.saber11.questions.domain.usecase;

import com.saber11.questions.domain.model.Area;
import com.saber11.questions.domain.model.Question;
import com.saber11.questions.domain.model.gateway.QuestionGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestionUseCaseTest {

    @Mock
    private QuestionGateway questionGateway;

    @InjectMocks
    private QuestionUseCase questionUseCase;

    @Test
    void createQuestionValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("optionA");
        question.setOptionB("optionB");
        question.setOptionC("optionC");
        question.setOptionD("optionD");
        question.setJustification("justification");
        Mockito.when(questionGateway.createQuestion(question)).thenReturn(question);
        //Act
        Question result = questionUseCase.createQuestion(question);

        //Assert
        assertEquals(question.getQuestion(), result.getQuestion());


    }

    @Test
    void createQuestionSinObjetoValido() {
        //Arrange
        Question question = null;
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Question cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionSinAreaNoValido() {
        //Arrange
        Question question = new Question();
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Area id cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionSinQuestionNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Question question cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionConQuestionVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Question question cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionSinAnswerNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Answer cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionConAnswerVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Answer cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionSinOptionANoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Option A cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionConOptionAVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Option A cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionSinOptionBNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("optionA");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Option B cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionConOptionBVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("OptionA");
        question.setOptionB(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Option B cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionSinOptionCNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("optionA");
        question.setOptionB("optionB");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Option C cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionConOptionCVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("OptionA");
        question.setOptionB("OptionB");
        question.setOptionC(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Option C cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionSinOptionDNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("optionA");
        question.setOptionB("optionB");
        question.setOptionC("optionC");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Option D cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionConOptionDVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("OptionA");
        question.setOptionB("OptionB");
        question.setOptionC("OptionC");
        question.setOptionD(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Option D cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionSinJustificationNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("optionA");
        question.setOptionB("optionB");
        question.setOptionC("optionC");
        question.setOptionD("optionD");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Justification cannot be null",exception.getMessage());

    }

    @Test
    void createQuestionConJustificationVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("OptionA");
        question.setOptionB("OptionB");
        question.setOptionC("OptionC");
        question.setOptionD("OptionD");
        question.setJustification(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.createQuestion(question);});
        assertEquals("Justification cannot be null",exception.getMessage());

    }









    @Test
    void findQuestionByIdValido() {
        //Arrange
        Long id = 1L;
        Question question = new Question();
        question.setId(id);
        Mockito.when(questionGateway.findQuestionById(id)).thenReturn(question);
        //Act
        Question result = questionUseCase.findQuestionById(id);

        //Assert
        assertEquals(id, result.getId());
    }

    @Test
    void findQuestionByIdNoValido() {
        //Arrange
        Long id = null;
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.findQuestionById(id);});
        assertEquals("Question id cannot be null",exception.getMessage());
    }

    @Test
    void findQuestionsByAreaValido() {
        //Arrange
        Area area= Area.MATEMATICAS;
        List<Question> questions = new ArrayList<>();
        Mockito.when(questionGateway.findQuestionsByArea(area)).thenReturn(questions);
        //Act
        List<Question> result = questionUseCase.findQuestionsByArea(area);
        //Assert
        assertEquals(questions, result);
    }

    @Test
    void findQuestionsByAreaNoValido() {
        //Arrange
        Area area= null;
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> {questionUseCase.findQuestionsByArea(area);});

        assertEquals("Area cannot be null",exception.getMessage());

    }

    @Test
    void findAllQuestions() {
        //Arrange
        List<Question> questions = new ArrayList<>();
        Mockito.when(questionGateway.findAllQuestions()).thenReturn(questions);
        //Act
        List<Question> result = questionUseCase.findAllQuestions();
        //Assert
        assertEquals(questions, result);
    }

    @Test
    void updateQuestionValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("optionA");
        question.setOptionB("optionB");
        question.setOptionC("optionC");
        question.setOptionD("optionD");
        question.setJustification("justification");
        Mockito.when(questionGateway.updateQuestion(question)).thenReturn(question);
        //Act
        Question result = questionUseCase.updateQuestion(question);
        //Assert
        assertEquals(question.getQuestion(), result.getQuestion());
    }

    @Test
    void updateQuestionSinObjetoValido() {
        //Arrange
        Question question = null;
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Question cannot be null",exception.getMessage());

    }
    @Test
    void updateQuestionSinIdNoValido() {
        //Arrange
        Question question = new Question();
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Question id cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionSinAreaNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Area id cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionSinQuestionNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Question question cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionConQuestionVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Question question cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionSinAnswerNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Answer cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionConAnswerVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Answer cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionSinOptionANoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Option A cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionConOptionAVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Option A cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionSinOptionBNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("optionA");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Option B cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionConOptionBVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("OptionA");
        question.setOptionB(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Option B cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionSinOptionCNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("optionA");
        question.setOptionB("optionB");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Option C cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionConOptionCVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("OptionA");
        question.setOptionB("OptionB");
        question.setOptionC(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Option C cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionSinOptionDNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("optionA");
        question.setOptionB("optionB");
        question.setOptionC("optionC");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Option D cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionConOptionDVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("OptionA");
        question.setOptionB("OptionB");
        question.setOptionC("OptionC");
        question.setOptionD(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Option D cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionSinJustificationNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("optionA");
        question.setOptionB("optionB");
        question.setOptionC("optionC");
        question.setOptionD("optionD");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Justification cannot be null",exception.getMessage());

    }

    @Test
    void updateQuestionConJustificationVaciaNoValido() {
        //Arrange
        Question question = new Question();
        question.setId(1L);
        question.setArea(Area.MATEMATICAS);
        question.setQuestion("question");
        question.setAnswer("answer");
        question.setOptionA("OptionA");
        question.setOptionB("OptionB");
        question.setOptionC("OptionC");
        question.setOptionD("OptionD");
        question.setJustification(" ");
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {questionUseCase.updateQuestion(question);});
        assertEquals("Justification cannot be null",exception.getMessage());

    }

    @Test
    void deleteQuestionByIdValido() {
        //Arrange
        Long id = 1L;
        //Act
        questionUseCase.deleteQuestionById(id);
        //Assert
        Mockito.verify(questionGateway, Mockito.times(1)).deleteQuestionById(id);
    }

    @Test
    void deleteQuestionByIdNoValido() {
        //Arrange
        Long id = null;
        //Act y Assert
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> {questionUseCase.deleteQuestionById(id);});

        assertEquals("Question id cannot be null",exception.getMessage());


    }
}