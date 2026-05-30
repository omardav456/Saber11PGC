package com.saber11.notificationemail.infraestructure.entry_points;

import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.usecase.NotificationUseCase;
import com.saber11.notificationemail.infraestructure.dto.NotificationRequest;
import com.saber11.notificationemail.infraestructure.mapper.NotificationMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class NotificationControllerTest {

    private NotificationUseCase notificationUseCase;
    private NotificationMapper notificationMapper;
    private NotificationController controller;

    @BeforeEach
    void setUp() {
        notificationUseCase = mock(NotificationUseCase.class);
        notificationMapper = mock(NotificationMapper.class);
        controller = new NotificationController(notificationUseCase, notificationMapper);
    }

    @Test
    void sendEmailReturnsOk() {
        NotificationRequest request = new NotificationRequest();
        request.setTo("test@test.com");
        request.setStudentName("Juan");
        request.setPlatformLink("http://saber11.com");

        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setPlatformLink("http://saber11.com");

        when(notificationMapper.toNotification(request)).thenReturn(notification);
        when(notificationUseCase.sendEmail(notification)).thenReturn("Correo de bienvenida enviado");

        ResponseEntity<String> response = controller.sendEmail(request);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Correo de bienvenida enviado", response.getBody());
    }

    @Test
    void sendRegisterReturnsOk() {
        NotificationRequest request = new NotificationRequest();
        request.setTo("test@test.com");
        request.setStudentName("Juan");

        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");

        when(notificationMapper.toNotification(request)).thenReturn(notification);
        when(notificationUseCase.sendRegisterSuccess(notification)).thenReturn("Correo de registro enviado");

        ResponseEntity<String> response = controller.sendRegisterSuccess(request);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Correo de registro enviado", response.getBody());
    }

    @Test
    void sendResultReturnsOk() {
        NotificationRequest request = new NotificationRequest();
        request.setTo("test@test.com");
        request.setStudentName("Juan");
        request.setScore("350");

        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setScore("350");

        when(notificationMapper.toNotification(request)).thenReturn(notification);
        when(notificationUseCase.sendSimulationResult(notification)).thenReturn("Resultado enviado correctamente");

        ResponseEntity<String> response = controller.sendSimulationResult(request);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Resultado enviado correctamente", response.getBody());
    }

    @Test
    void sendLinkReturnsOk() {
        NotificationRequest request = new NotificationRequest();
        request.setTo("test@test.com");
        request.setStudentName("Juan");
        request.setExamLink("http://exam.com");

        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setExamLink("http://exam.com");

        when(notificationMapper.toNotification(request)).thenReturn(notification);
        when(notificationUseCase.sendExamLink(notification)).thenReturn("Link simulacro enviado");

        ResponseEntity<String> response = controller.sendExamLink(request);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Link simulacro enviado", response.getBody());
    }

    @Test
    void sendEmailThrowsWhenUseCaseFails() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        request.setTo("test@test.com");
        request.setStudentName("Juan");
        request.setPlatformLink("http://saber11.com");
        Notification notification = new Notification();
        when(notificationMapper.toNotification(request)).thenReturn(notification);
        when(notificationUseCase.sendEmail(notification)).thenThrow(new RuntimeException("SMTP error"));

        // Act & Assert
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
                () -> controller.sendEmail(request));

        Assertions.assertEquals("SMTP error", ex.getMessage());
    }

    @Test
    void sendRegisterThrowsWhenUseCaseFails() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        request.setTo("test@test.com");
        request.setStudentName("Juan");
        Notification notification = new Notification();
        when(notificationMapper.toNotification(request)).thenReturn(notification);
        when(notificationUseCase.sendRegisterSuccess(notification)).thenThrow(new RuntimeException("SMTP error"));

        // Act & Assert
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
                () -> controller.sendRegisterSuccess(request));

        Assertions.assertEquals("SMTP error", ex.getMessage());
    }

    @Test
    void sendResultThrowsWhenUseCaseFails() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        request.setTo("test@test.com");
        request.setStudentName("Juan");
        request.setScore("350");
        Notification notification = new Notification();
        when(notificationMapper.toNotification(request)).thenReturn(notification);
        when(notificationUseCase.sendSimulationResult(notification)).thenThrow(new RuntimeException("SMTP error"));

        // Act & Assert
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
                () -> controller.sendSimulationResult(request));

        Assertions.assertEquals("SMTP error", ex.getMessage());
    }

    @Test
    void sendLinkThrowsWhenUseCaseFails() {
        // Arrange
        NotificationRequest request = new NotificationRequest();
        request.setTo("test@test.com");
        request.setStudentName("Juan");
        request.setExamLink("http://exam.com");
        Notification notification = new Notification();
        when(notificationMapper.toNotification(request)).thenReturn(notification);
        when(notificationUseCase.sendExamLink(notification)).thenThrow(new RuntimeException("SMTP error"));

        // Act & Assert
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
                () -> controller.sendExamLink(request));

        Assertions.assertEquals("SMTP error", ex.getMessage());
    }
}
