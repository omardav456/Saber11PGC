package com.saber11.exam.infraestructure.driver_adapters.jpa_repository;

import com.saber11.exam.domain.model.Categoria;
import com.saber11.exam.domain.model.Simulacro;
import com.saber11.exam.infraestructure.mapper.MapperSimulacro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulacroDataGatewayImpTest {

    @Mock
    private SimulacroDataJpaRepository simulacroDataJpaRepository;

    @Mock
    private MapperSimulacro mapperSimulacro;

    @InjectMocks
    private SimulacroDataGatewayImp gateway;

    @Test
    void createSimulacroSavesAndReturns() {
        Simulacro simulacro = new Simulacro();
        simulacro.setCategoria(Categoria.REAL);
        SimulacroData data = new SimulacroData();
        SimulacroData savedData = new SimulacroData();
        savedData.setId(1L);
        savedData.setCategoria(Categoria.REAL);

        when(mapperSimulacro.toSimulacroData(simulacro)).thenReturn(data);
        when(simulacroDataJpaRepository.save(data)).thenReturn(savedData);
        when(mapperSimulacro.toSimulacro(savedData)).thenReturn(simulacro);

        Simulacro result = gateway.createSimulacro(simulacro);

        assertNotNull(result);
        assertEquals(Categoria.REAL, result.getCategoria());
        verify(mapperSimulacro).toSimulacroData(simulacro);
        verify(simulacroDataJpaRepository).save(data);
        verify(mapperSimulacro).toSimulacro(savedData);
    }

    @Test
    void createSimulacroAutoSavesWithRealCategory() {

        // Arrange
        List<Long> questionIds = List.of(1L, 2L);

        SimulacroData savedData = new SimulacroData();
        savedData.setId(1L);
        savedData.setCategoria(Categoria.REAL);
        savedData.setQuestionsId(questionIds); // 👈 CAMBIO CLAVE

        when(simulacroDataJpaRepository.save(any(SimulacroData.class)))
                .thenReturn(savedData);

        when(mapperSimulacro.toSimulacro(any(SimulacroData.class)))
                .thenAnswer(invocation -> {
                    SimulacroData d = invocation.getArgument(0);
                    return new Simulacro(
                            d.getId(),
                            d.getCategoria(),
                            d.getTiempoLimite(),
                            d.getQuestionsId()
                    );
                });

        // Act
        Simulacro result = gateway.createSimulacroAuto(questionIds);

        // Assert
        assertNotNull(result);
        assertEquals(Categoria.REAL, result.getCategoria());
        assertEquals(2, result.getQuestionIds().size());

        verify(simulacroDataJpaRepository).save(any(SimulacroData.class));
    }

    @Test
    void getSimulacroByCategoriaReturnsList() {
        Categoria categoria = Categoria.REAL;
        SimulacroData data = new SimulacroData();
        data.setId(1L);
        data.setCategoria(categoria);
        List<SimulacroData> dataList = List.of(data);

        when(simulacroDataJpaRepository.findByCategoria(categoria)).thenReturn(Optional.of(dataList));
        when(mapperSimulacro.toSimulacro(data)).thenReturn(new Simulacro(1L, categoria, null, null));

        List<Simulacro> result = gateway.getSimulacroByCategoria(categoria);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(categoria, result.get(0).getCategoria());
    }

    @Test
    void getSimulacroByCategoriaThrowsWhenNotFound() {
        Categoria categoria = Categoria.REAL;
        when(simulacroDataJpaRepository.findByCategoria(categoria)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> gateway.getSimulacroByCategoria(categoria));
        assertEquals("No existe", exception.getMessage());
    }

    @Test
    void deleteSimulacroDeletesById() {
        Long id = 1L;

        gateway.deleteSimulacro(id);

        verify(simulacroDataJpaRepository).deleteById(id);
    }

    @Test
    void getSimulacroByIdReturnsSimulacro() {
        Long id = 1L;
        SimulacroData data = new SimulacroData();
        data.setId(id);

        when(simulacroDataJpaRepository.findById(id)).thenReturn(Optional.of(data));
        when(mapperSimulacro.toSimulacro(data)).thenReturn(new Simulacro(id, null, null, null));

        Simulacro result = gateway.getSimulacroById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void getSimulacroByIdThrowsWhenNotFound() {
        Long id = 1L;
        when(simulacroDataJpaRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> gateway.getSimulacroById(id));
        assertEquals("No existe", exception.getMessage());
    }

    @Test
    void getAllSimulacroReturnsAll() {
        SimulacroData data1 = new SimulacroData();
        data1.setId(1L);
        SimulacroData data2 = new SimulacroData();
        data2.setId(2L);
        List<SimulacroData> dataList = List.of(data1, data2);

        when(simulacroDataJpaRepository.findAll()).thenReturn(dataList);
        when(mapperSimulacro.toSimulacro(data1)).thenReturn(new Simulacro(1L, null, null, null));
        when(mapperSimulacro.toSimulacro(data2)).thenReturn(new Simulacro(2L, null, null, null));

        List<Simulacro> result = gateway.getAllSimulacro();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
