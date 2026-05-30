package com.saber11.exam.domain.usecase;

import com.saber11.exam.domain.model.Area;
import com.saber11.exam.domain.model.Categoria;
import com.saber11.exam.domain.model.Question;
import com.saber11.exam.domain.model.Simulacro;
import com.saber11.exam.domain.model.gateway.QuestionGateway;
import com.saber11.exam.domain.model.gateway.SimulacroGateway;
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
class SimulacroUseCaseTest {

    @Mock
    private SimulacroGateway simulacroGateway;
    @Mock
    private QuestionGateway questionGateway;

    @InjectMocks
    private SimulacroUseCase simulacroUseCase;

    @Test
    void createSimulacroValido() {
        //Arrange
        Simulacro simulacro = new Simulacro();
        simulacro.setCategoria(Categoria.REAL);
        Question question = new Question();
        Question question2 = new Question();
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        questions.add(question2);
        simulacro.setQuestions(questions);

        Mockito.when(simulacroGateway.createSimulacro(simulacro)).thenReturn(simulacro);
        //Act
        Simulacro simulacroRecibido= simulacroUseCase.createSimulacro(simulacro);
        //Assert
        assertEquals(simulacro, simulacroRecibido);
    }

    @Test
    void createSimulacroNullNoValido() {
        // Arrange
        Simulacro simulacro = null;

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> simulacroUseCase.createSimulacro(simulacro)
        );

        // Assert
        assertEquals("simulacro is null", exception.getMessage());
    }

    @Test
    void createSimulacroCategoriaNoValido() {
        // Arrange
        Simulacro simulacro = new Simulacro();
        simulacro.setCategoria(null);

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> simulacroUseCase.createSimulacro(simulacro)
        );

        // Assert
        assertEquals("simulacro.categoria is null", exception.getMessage());
    }
    @Test
    void createSimulacroQuestionNullNoValido() {
        // Arrange
        Simulacro simulacro = new Simulacro();
        simulacro.setCategoria(Categoria.REAL);
        simulacro.setQuestions(null);

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> simulacroUseCase.createSimulacro(simulacro)
        );

        // Assert
        assertEquals("simulacro.questions is null", exception.getMessage());
    }
    @Test
    void createSimulacroQuestionEmptyNoValido() {
        // Arrange
        Simulacro simulacro = new Simulacro();
        simulacro.setCategoria(Categoria.REAL);
        simulacro.setQuestions(List.of());

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> simulacroUseCase.createSimulacro(simulacro)
        );

        // Assert
        assertEquals("simulacro.questions is empty", exception.getMessage());
    }


    @Test
    void createSimulacroAuto() {
        //Arrange
        Simulacro simulacro = new Simulacro();
        Question question = new Question();
        question.setArea(Area.MATEMATICAS);
        Question question2 = new Question();
        question2.setArea(Area.MATEMATICAS);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        questions.add(question2);
        simulacro.setQuestions(questions);
        Mockito.when(questionGateway.getQuestions()).thenReturn(questions);
        Mockito.when(simulacroGateway.createSimulacroAuto(questions)).thenReturn(simulacro);

        //Act
        Simulacro simulacro1= simulacroUseCase.createSimulacroAuto();
        //Assert
        assertEquals(simulacro, simulacro1);
    }

    @Test
    void getSimulacroByCategoriaValido() {
        //Arrange
        Categoria categoria= Categoria.REAL;
        Simulacro simulacro = new Simulacro();
        List<Simulacro> simulacros = new ArrayList<>();
        simulacros.add(simulacro);
        simulacro.setCategoria(categoria);
        Mockito.when(simulacroGateway.getSimulacroByCategoria(categoria)).thenReturn(simulacros);
        //Act
        List<Simulacro> simulacros1= simulacroUseCase.getSimulacroByCategoria(categoria);

        //Assert
        assertEquals(simulacros, simulacros1);
    }

    @Test
    void getSimulacroByCategoriaNoValido() {

        // Arrange
        Categoria categoria = null;

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> simulacroUseCase.getSimulacroByCategoria(categoria)
        );

        // Assert
        assertEquals("categoria is null", exception.getMessage());
    }


    @Test
    void deleteSimulacroValido() {
        //Arrange
        Long id=1L;
        //Act
            simulacroUseCase.deleteSimulacro(id);
        //Assert
        Mockito.verify(simulacroGateway).deleteSimulacro(id);

    }
    @Test
    void deleteSimulacroIdNullNoValido() {
        // Arrange
        Long id = null;

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> simulacroUseCase.deleteSimulacro(id)
        );

        // Assert
        assertEquals("id is null", exception.getMessage());

    }
    @Test
    void deleteSimulacroIdZeroNoValido() {
        // Arrange
        Long id = 0L;

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> simulacroUseCase.deleteSimulacro(id)
        );

        // Assert
        assertEquals("id cant be 0", exception.getMessage());
    }

    @Test
    void getSimulacroById() {
        //Arrange
        Long id=1L;
        //Act
        simulacroUseCase.getSimulacroById(id);
        //Assert
        Mockito.verify(simulacroGateway).getSimulacroById(id);

    }

    @Test
    void getSimulacroByIdNullNoValido() {

        // Arrange
        Long id = null;

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> simulacroUseCase.getSimulacroById(id)
        );

        // Assert
        assertEquals("id is null", exception.getMessage());
    }

    @Test
    void getSimulacroByIdZeroNoValido() {

        // Arrange
        Long id = 0L;

        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> simulacroUseCase.getSimulacroById(id)
        );

        // Assert
        assertEquals("id cant be 0", exception.getMessage());
    }

    @Test
    void getAllSimulacro() {
        //Arrange
        Simulacro simulacro = new Simulacro();
        List<Simulacro> simulacros = new ArrayList<>();
        simulacros.add(simulacro);
        Mockito.when(simulacroGateway.getAllSimulacro()).thenReturn(simulacros);
        //Act
        simulacroUseCase.getAllSimulacro();
        //Assert
        Mockito.verify(simulacroGateway).getAllSimulacro();
    }
}