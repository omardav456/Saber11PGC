package com.saber11.exam.application.config;

import com.saber11.exam.domain.model.gateway.QuestionGateway;
import com.saber11.exam.domain.model.gateway.SimulacroGateway;
import com.saber11.exam.domain.usecase.SimulacroUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UseCaseConfigTest {

    @Mock
    private SimulacroGateway simulacroGateway;

    @Mock
    private QuestionGateway questionGateway;

    @Test
    void simulacroUseCaseCreatesBean() {
        UseCaseConfig config = new UseCaseConfig();

        SimulacroUseCase useCase = config.simulacroUseCase(simulacroGateway, questionGateway);

        assertNotNull(useCase);
        assertInstanceOf(SimulacroUseCase.class, useCase);
    }
}
