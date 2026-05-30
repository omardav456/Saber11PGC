package com.saber11.auth.application.config;

import com.saber11.auth.domain.model.gateway.EncryptGateway;
import com.saber11.auth.domain.model.gateway.UserGateway;
import com.saber11.auth.domain.usecase.UserUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UseCaseConfigTest {

    @Mock
    private UserGateway userGateway;

    @Mock
    private EncryptGateway encryptGateway;

    @Test
    void userUseCase_BeanIsCreated() {
        UseCaseConfig config = new UseCaseConfig();
        UserUseCase useCase = config.userUseCase(userGateway, encryptGateway);
        assertNotNull(useCase);
    }
}
