package com.saber11.exam.application.config;

import com.saber11.exam.domain.model.gateway.QuestionGateway;
import com.saber11.exam.domain.model.gateway.SimulacroGateway;
import com.saber11.exam.domain.usecase.SimulacroUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public SimulacroUseCase simulacroUseCase(SimulacroGateway simulacroGateway, QuestionGateway questionGateway) {
        return new SimulacroUseCase(simulacroGateway, questionGateway);
    }
}
