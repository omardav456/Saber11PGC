package com.saber11.questions.application.config;

import com.saber11.questions.domain.model.gateway.QuestionGateway;
import com.saber11.questions.domain.usecase.QuestionUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UseCaseConfigTest {

    @Mock
    private QuestionGateway questionGateway;

    private final UseCaseConfig config = new UseCaseConfig();

    @Test
    void questionUseCase_BeanCreated() {
        QuestionUseCase useCase = config.questionUseCase(questionGateway);

        assertNotNull(useCase);
        assertInstanceOf(QuestionUseCase.class, useCase);
    }
}
