package com.saber11.notificationemail.application.config;

import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;
import com.saber11.notificationemail.domain.usecase.NotificationUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class UseCaseConfigTest {

    private final UseCaseConfig config = new UseCaseConfig();

    @Test
    void notificationUseCaseBeanIsCreated() {
        NotificationGateway gateway = mock(NotificationGateway.class);
        NotificationUseCase useCase = config.notificationUseCase(gateway);
        Assertions.assertNotNull(useCase);
    }
}
