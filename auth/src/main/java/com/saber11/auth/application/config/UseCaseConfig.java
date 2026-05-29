package com.saber11.auth.application.config;


import com.saber11.auth.domain.model.gateway.EncryptGateway;
import com.saber11.auth.domain.model.gateway.UserGateway;
import com.saber11.auth.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public UserUseCase userUseCase(UserGateway userGateway, EncryptGateway encryptGateway){
        return new UserUseCase(userGateway, encryptGateway);
    }
}