package com.saber11.notificationemail.domain.UseCase;

import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;

import com.saber11.notificationemail.domain.usecase.NotificationUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

class NotificationUseCaseTest {
    @Mock
    private NotificationGateway notificationGateway;
    @InjectMocks
    private NotificationUseCase notificationUseCase;

    @Test
    void sendEmailSuccess() {
        //Arrange
        Notification notification = new Notification();

        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setPlatformLink("http://saber11.com");

        //Act
        String response =
                notificationUseCase.sendEmail(notification);
        //Assert
        Assertions.assertEquals("Correo de bienvenida enviado", response);

        Mockito.<NotificationGateway>verify(notificationGateway).sendEmail(notification);
    }

    @Test
    void sendEmailWithoutTo() {
        //Arrange
        Notification notification = new Notification();

        notification.setStudentName("Juan");
        notification.setPlatformLink("http://saber11.com");
        //Act
        Exception exception =
                Assertions.<RuntimeException>assertThrows(
                        RuntimeException.class,
                        () -> notificationUseCase.sendEmail(notification)
                );
        //Assert
        Assertions.assertEquals(
                "Correo destino obligatorio",
                exception.getMessage()
        );
    }

    @Test
    void sendEmailWithoutName() {
        //Arrange
        Notification notification = new Notification();

        notification.setTo("test@test.com");
        notification.setPlatformLink("http://saber11.com");
        //Act
        Exception exception =
                Assertions.<RuntimeException>assertThrows(
                        RuntimeException.class,
                        () -> notificationUseCase.sendEmail(notification)
                );
        //Assert
        Assertions.assertEquals(
                "Nombre obligatorio",
                exception.getMessage()
        );
    }

    @Test
    void sendEmailWithoutPlatformLink() {
        //Arrange
        Notification notification = new Notification();

        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        //Act
        Exception exception =
                Assertions.<RuntimeException>assertThrows(
                        RuntimeException.class,
                        () -> notificationUseCase.sendEmail(notification)
                );
        //Assert
        Assertions.assertEquals(
                "Link plataforma obligatorio",
                exception.getMessage()
        );
    }

    @Test
    void sendRegisterSuccessOk() {
        //Arrange
        Notification notification = new Notification();

        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        //Act
        String response =
                notificationUseCase
                        .sendRegisterSuccess(notification);
        //Assert
        Assertions.assertEquals(
                "Correo de registro enviado",
                response
        );

        Mockito.<NotificationGateway>verify(notificationGateway)
                .sendRegisterSuccess(notification);
    }

    @Test
    void sendRegisterWithoutEmail() {
        //Arrange
        Notification notification = new Notification();

        notification.setStudentName("Juan");
        //Act
        Exception exception =
                Assertions.<RuntimeException>assertThrows(
                        RuntimeException.class,
                        () -> notificationUseCase
                                .sendRegisterSuccess(notification)
                );
        //Assert
        Assertions.assertEquals(
                "Correo destino obligatorio",
                exception.getMessage()
        );
    }


    @Test
    void sendSimulationResultSuccess() {
        //Arrange
        Notification notification = new Notification();

        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setScore("350");

        //Act
        String response =
                notificationUseCase
                        .sendSimulationResult(notification);
        //Assert
        Assertions.assertEquals(
                "Resultado enviado correctamente",
                response
        );

        Mockito.<NotificationGateway>verify(notificationGateway)
                .sendSimulationResult(notification);
    }

    @Test
    void sendSimulationResultWithoutScore() {
        //Arrange
        Notification notification = new Notification();

        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        //Act
        Exception exception =
                Assertions.<RuntimeException>assertThrows(
                        RuntimeException.class,
                        () -> notificationUseCase
                                .sendSimulationResult(notification)
                );
        //Assert
        Assertions.assertEquals(
                "Puntaje obligatorio",
                exception.getMessage()
        );
    }


    @Test
    void sendExamLinkSuccess() {
        //Arrange
        Notification notification = new Notification();

        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setExamLink("http://exam.com");
        //Act
        String response =
                notificationUseCase
                        .sendExamLink(notification);
        //Assert
        Assertions.assertEquals(
                "Link simulacro enviado",
                response
        );

        Mockito.<NotificationGateway>verify(notificationGateway)
                .sendExamLink(notification);
    }

    @Test
    void sendExamLinkWithoutLink() {

        //Arrange
        Notification notification = new Notification();

        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        //Act
        Exception exception =
                Assertions.<RuntimeException>assertThrows(
                        RuntimeException.class,
                        () -> notificationUseCase
                                .sendExamLink(notification)
                );
        //Assert
        Assertions.assertEquals(
                "Link del simulacro obligatorio",
                exception.getMessage()
        );
    }
}

