package com.saber11.exam.infraestructure.entry_points;

import com.saber11.exam.domain.model.Categoria;
import com.saber11.exam.domain.model.Simulacro;
import com.saber11.exam.domain.usecase.SimulacroUseCase;
import com.saber11.exam.infraestructure.driver_adapters.jpa_repository.SimulacroData;
import com.saber11.exam.infraestructure.mapper.MapperSimulacro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

    @Mock
    private SimulacroUseCase simulacroUseCase;

    @Mock
    private MapperSimulacro mapperSimulacro;

    @InjectMocks
    private SimulacroContrroller controller;

    @Test
    void createSimulacroReturnsCreated() {
        Simulacro simulacro = new Simulacro();
        simulacro.setId(1L);
        simulacro.setCategoria(Categoria.REAL);
        SimulacroData data = new SimulacroData();

        when(mapperSimulacro.toSimulacro(data)).thenReturn(simulacro);
        when(simulacroUseCase.createSimulacro(simulacro)).thenReturn(simulacro);

        ResponseEntity<Simulacro> response = controller.createSimulacro(data);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals(Categoria.REAL, response.getBody().getCategoria());
    }

    @Test
    void createSimulacroAutoReturnsCreated() {
        Simulacro simulacro = new Simulacro();
        simulacro.setId(1L);

        when(simulacroUseCase.createSimulacroAuto()).thenReturn(simulacro);

        ResponseEntity<Simulacro> response = controller.createSimulacroAuto();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void getSimulacroByCategoriaReturnsOk() {
        Simulacro simulacro = new Simulacro();
        simulacro.setId(1L);
        simulacro.setCategoria(Categoria.AREA);

        when(simulacroUseCase.getSimulacroByCategoria(Categoria.AREA))
                .thenReturn(List.of(simulacro));

        ResponseEntity<List<Simulacro>> response = controller.getSimulacroByCategoria(Categoria.AREA);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(Categoria.AREA, response.getBody().get(0).getCategoria());
    }

    @Test
    void deleteSimulacroReturnsOk() {
        doNothing().when(simulacroUseCase).deleteSimulacro(1L);

        ResponseEntity response = controller.deleteSimulacro(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(simulacroUseCase).deleteSimulacro(1L);
    }

    @Test
    void getSimulacroByIdReturnsOk() {
        Simulacro simulacro = new Simulacro();
        simulacro.setId(1L);

        when(simulacroUseCase.getSimulacroById(1L)).thenReturn(simulacro);

        ResponseEntity<Simulacro> response = controller.getSimulacroById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void getAllSimulacroReturnsOk() {
        Simulacro simulacro = new Simulacro();
        simulacro.setId(1L);

        when(simulacroUseCase.getAllSimulacro()).thenReturn(List.of(simulacro));

        ResponseEntity<List<Simulacro>> response = controller.getAllSimulacro();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }
}
