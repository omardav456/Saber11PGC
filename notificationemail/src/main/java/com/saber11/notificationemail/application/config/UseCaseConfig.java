package com.saber11.notificationemail.application.config;

import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;
import com.saber11.notificationemail.domain.usecase.NotificationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public NotificationUseCase notificationUseCase(
            NotificationGateway notificationGateway){
        return new NotificationUseCase(notificationGateway);
    }
}
