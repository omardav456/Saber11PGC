package com.saber11.exam.infraestructure.mapper;

import com.saber11.exam.domain.model.Categoria;
import com.saber11.exam.domain.model.Question;
import com.saber11.exam.domain.model.Simulacro;
import com.saber11.exam.infraestructure.driver_adapters.jpa_repository.SimulacroData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperSimulacroTest {

    private final MapperSimulacro mapper = new MapperSimulacro();

    @Test
    void toSimulacroMapsAllFields() {
        Question question = new Question();
        question.setId(1L);
        List<Long> questions = List.of(question.getId());
        SimulacroData data = new SimulacroData(1L, Categoria.REAL, 60, questions);

        Simulacro result = mapper.toSimulacro(data);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(Categoria.REAL, result.getCategoria());
        assertEquals(60, result.getTiempoLimite());
        assertEquals(1, result.getQuestionIds().size());
        assertEquals(1L, result.getQuestionIds().get(0));
    }

    @Test
    void toSimulacroDataMapsAllFields() {
        Question question = new Question();
        question.setId(2L);
        List<Long> questions = List.of(question.getId());
        Simulacro simulacro = new Simulacro(2L, Categoria.REAL, 90, questions);

        SimulacroData result = mapper.toSimulacroData(simulacro);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals(Categoria.REAL, result.getCategoria());
        assertEquals(90, result.getTiempoLimite());
        assertEquals(1, result.getQuestionsId().size());
        assertEquals(2L, result.getQuestionsId().get(0));
    }

    @Test
    void toSimulacroWithNullFields() {
        SimulacroData data = new SimulacroData(null, null, null, null);

        Simulacro result = mapper.toSimulacro(data);

        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getCategoria());
        assertNull(result.getTiempoLimite());
        assertNull(result.getQuestionIds());
    }

    @Test
    void toSimulacroDataWithNullFields() {
        Simulacro simulacro = new Simulacro(null, null, null, null);

        SimulacroData result = mapper.toSimulacroData(simulacro);

        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getCategoria());
        assertNull(result.getTiempoLimite());
        assertNull(result.getQuestionsId());
    }
}
