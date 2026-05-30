package com.saber11.notificationemail.infraestructure.driver_adapters.smtp;

import com.saber11.notificationemail.domain.model.Notification;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationGatewayImplTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private NotificationGatewayImpl notificationGateway;

    private MimeMessage mimeMessage;

    @Captor
    private ArgumentCaptor<MimeMessage> mimeMessageCaptor;

    @BeforeEach
    void setUp() {
        Session session = Session.getDefaultInstance(new Properties());
        mimeMessage = new MimeMessage(session);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @Test
    void sendEmailSuccess() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setPlatformLink("http://saber11.com");

        notificationGateway.sendEmail(notification);

        verify(javaMailSender).send(mimeMessageCaptor.capture());
        Assertions.assertNotNull(mimeMessageCaptor.getValue());
    }

    @Test
    void sendEmailThrowsException() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setPlatformLink("http://saber11.com");

        when(javaMailSender.createMimeMessage()).thenThrow(new RuntimeException("SMTP error"));

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

        notificationGateway.sendRegisterSuccess(notification);

        verify(javaMailSender).send(mimeMessageCaptor.capture());
        Assertions.assertNotNull(mimeMessageCaptor.getValue());
    }

    @Test
    void sendRegisterSuccessThrowsException() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");

        when(javaMailSender.createMimeMessage()).thenThrow(new RuntimeException("SMTP error"));

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

        notificationGateway.sendSimulationResult(notification);

        verify(javaMailSender).send(mimeMessageCaptor.capture());
        Assertions.assertNotNull(mimeMessageCaptor.getValue());
    }

    @Test
    void sendSimulationResultWithPdf() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setScore("350");
        notification.setPdfPath("src/test/resources/test.pdf");

        notificationGateway.sendSimulationResult(notification);

        verify(javaMailSender).send(mimeMessageCaptor.capture());
        Assertions.assertNotNull(mimeMessageCaptor.getValue());
    }

    @Test
    void sendSimulationResultThrowsException() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setScore("350");

        when(javaMailSender.createMimeMessage()).thenThrow(new RuntimeException("SMTP error"));

        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> notificationGateway.sendSimulationResult(notification)
        );

        Assertions.assertEquals("SMTP error", exception.getMessage());
    }

    @Test
    void sendExamLinkSuccess() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setExamLink("http://exam.com");

        notificationGateway.sendExamLink(notification);

        verify(javaMailSender).send(mimeMessageCaptor.capture());
        Assertions.assertNotNull(mimeMessageCaptor.getValue());
    }

    @Test
    void sendSimulationResultWithBlankPdfPath() {
        Notification notification = new Notification();
        notification.setTo("test@test.com");
        notification.setStudentName("Juan");
        notification.setScore("350");
        notification.setPdfPath("");

        notificationGateway.sendSimulationResult(notification);

        verify(javaMailSender).send(mimeMessageCaptor.capture());
        Assertions.assertNotNull(mimeMessageCaptor.getValue());
    }

    @Test
    void loadTemplateCatchBlockIsCovered() {
        NotificationGatewayImpl gateway = new NotificationGatewayImpl(javaMailSender) {
            @Override
            protected String loadTemplate(String templateName) {
                return super.loadTemplate("nonexistent-file.html");
            }
        };

        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage msg = new MimeMessage(session);
        when(javaMailSender.createMimeMessage()).thenReturn(msg);

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

        when(javaMailSender.createMimeMessage()).thenThrow(new RuntimeException("SMTP error"));

        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> notificationGateway.sendExamLink(notification)
        );

        Assertions.assertEquals("SMTP error", exception.getMessage());
    }
}
