package com.saber11.questions.application.config;

import com.saber11.questions.domain.model.gateway.QuestionGateway;
import com.saber11.questions.domain.usecase.QuestionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public QuestionUseCase questionUseCase(QuestionGateway questionGateway){
        return new QuestionUseCase(questionGateway);
    }
}
