package com.saber11.notificationemail.infraestructure.driver_adapters.smtp;

import com.saber11.notificationemail.domain.model.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationGatewayImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NotificationGatewayImpl notificationGateway;

    @Captor
    private ArgumentCaptor<Map<String, Object>> bodyCaptor;

    @Test
    void sendEmailSuccess() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setPlatformLink("http://saber11.com");

        when(restTemplate.postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        notificationGateway.sendEmail(notification);

        verify(restTemplate).postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        );
    }

    @Test
    void sendEmailThrowsException() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setPlatformLink("http://saber11.com");

        when(restTemplate.postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        )).thenThrow(new RuntimeException("mailtrap error"));

        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> notificationGateway.sendEmail(notification)
        );

        Assertions.assertTrue(exception.getMessage().contains("Error enviando correo"));
    }

    @Test
    void sendRegisterSuccess() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");

        when(restTemplate.postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        notificationGateway.sendRegisterSuccess(notification);

        verify(restTemplate).postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        );
    }

    @Test
    void sendRegisterSuccessThrowsException() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");

        when(restTemplate.postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        )).thenThrow(new RuntimeException("mailtrap error"));

        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> notificationGateway.sendRegisterSuccess(notification)
        );

        Assertions.assertTrue(exception.getMessage().contains("Error enviando correo"));
    }

    @Test
    void sendSimulationResultWithoutPdf() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setScore("350");

        when(restTemplate.postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        notificationGateway.sendSimulationResult(notification);

        verify(restTemplate).postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        );
    }

    @Test
    void sendSimulationResultWithPdf() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setScore("350");
        notification.setPdfPath("src/test/resources/test.pdf");

        when(restTemplate.postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        notificationGateway.sendSimulationResult(notification);

        verify(restTemplate).postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        );
    }

    @Test
    void sendSimulationResultThrowsException() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setScore("350");

        when(restTemplate.postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        )).thenThrow(new RuntimeException("API error"));

        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> notificationGateway.sendSimulationResult(notification)
        );

        Assertions.assertEquals("API error", exception.getMessage());
    }

    @Test
    void sendExamLinkSuccess() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setExamLink("http://exam.com");

        when(restTemplate.postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        notificationGateway.sendExamLink(notification);

        verify(restTemplate).postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        );
    }

    @Test
    void sendSimulationResultWithBlankPdfPath() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setScore("350");
        notification.setPdfPath("");

        when(restTemplate.postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        notificationGateway.sendSimulationResult(notification);

        verify(restTemplate).postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        );
    }

    @Test
    void loadTemplateCatchBlockIsCovered() {
        NotificationGatewayImpl gateway = new NotificationGatewayImpl(restTemplate) {
            @Override
            protected String loadTemplate(String templateName) {
                return super.loadTemplate("nonexistent-file.html");
            }
        };

        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setPlatformLink("http://saber11.com");

        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> gateway.sendEmail(notification)
        );

        Assertions.assertTrue(exception.getMessage().contains("Error enviando correo"));
    }

    @Test
    void sendExamLinkThrowsException() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setExamLink("http://exam.com");

        when(restTemplate.postForEntity(
                any(String.class),
                any(org.springframework.http.HttpEntity.class),
                eq(String.class)
        )).thenThrow(new RuntimeException("API error"));

        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> notificationGateway.sendExamLink(notification)
        );

        Assertions.assertEquals("API error", exception.getMessage());
    }
}
