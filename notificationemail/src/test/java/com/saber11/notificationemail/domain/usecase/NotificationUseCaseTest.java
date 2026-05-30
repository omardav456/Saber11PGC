package com.saber11.notificationemail.domain.usecase;

import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class NotificationUseCaseTest {

    private NotificationGateway notificationGateway;
    private NotificationUseCase useCase;

    @BeforeEach
    void setUp() {
        notificationGateway = mock(NotificationGateway.class);
        useCase = new NotificationUseCase(notificationGateway);
    }

    private Notification validWelcomeNotification() {
        Notification n = new Notification();
        n.setTo("test@test.com");
        n.setStudentName("Juan");
        n.setPlatformLink("http://saber11.com");
        return n;
    }

    private Notification validRegisterNotification() {
        Notification n = new Notification();
        n.setTo("test@test.com");
        n.setStudentName("Juan");
        return n;
    }

    private Notification validSimulationNotification() {
        Notification n = new Notification();
        n.setTo("test@test.com");
        n.setStudentName("Juan");
        n.setScore("350");
        return n;
    }

    private Notification validExamLinkNotification() {
        Notification n = new Notification();
        n.setTo("test@test.com");
        n.setStudentName("Juan");
        n.setExamLink("http://exam.com");
        return n;
    }

    @Test
    void sendEmailSuccess() {
        Notification n = validWelcomeNotification();

        String result = useCase.sendEmail(n);

        Assertions.assertEquals("Correo de bienvenida enviado", result);
        verify(notificationGateway).sendEmail(n);
    }

    @Test
    void sendEmailThrowsWhenToIsNull() {
        Notification n = validWelcomeNotification();
        n.setTo(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendEmail(n));

        Assertions.assertEquals("Correo destino obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendEmail(any());
    }

    @Test
    void sendEmailThrowsWhenToIsBlank() {
        Notification n = validWelcomeNotification();
        n.setTo("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendEmail(n));

        Assertions.assertEquals("Correo destino obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendEmail(any());
    }

    @Test
    void sendEmailThrowsWhenStudentNameIsNull() {
        Notification n = validWelcomeNotification();
        n.setStudentName(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendEmail(n));

        Assertions.assertEquals("Nombre obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendEmail(any());
    }

    @Test
    void sendEmailThrowsWhenStudentNameIsBlank() {
        Notification n = validWelcomeNotification();
        n.setStudentName("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendEmail(n));

        Assertions.assertEquals("Nombre obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendEmail(any());
    }

    @Test
    void sendEmailThrowsWhenPlatformLinkIsNull() {
        Notification n = validWelcomeNotification();
        n.setPlatformLink(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendEmail(n));

        Assertions.assertEquals("Link plataforma obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendEmail(any());
    }

    @Test
    void sendEmailThrowsWhenPlatformLinkIsBlank() {
        Notification n = validWelcomeNotification();
        n.setPlatformLink("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendEmail(n));

        Assertions.assertEquals("Link plataforma obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendEmail(any());
    }

    @Test
    void sendRegisterSuccessSuccess() {
        Notification n = validRegisterNotification();

        String result = useCase.sendRegisterSuccess(n);

        Assertions.assertEquals("Correo de registro enviado", result);
        verify(notificationGateway).sendRegisterSuccess(n);
    }

    @Test
    void sendRegisterSuccessThrowsWhenToIsNull() {
        Notification n = validRegisterNotification();
        n.setTo(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendRegisterSuccess(n));

        Assertions.assertEquals("Correo destino obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendRegisterSuccess(any());
    }

    @Test
    void sendRegisterSuccessThrowsWhenToIsBlank() {
        Notification n = validRegisterNotification();
        n.setTo("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendRegisterSuccess(n));

        Assertions.assertEquals("Correo destino obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendRegisterSuccess(any());
    }

    @Test
    void sendRegisterSuccessThrowsWhenStudentNameIsNull() {
        Notification n = validRegisterNotification();
        n.setStudentName(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendRegisterSuccess(n));

        Assertions.assertEquals("Nombre obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendRegisterSuccess(any());
    }

    @Test
    void sendRegisterSuccessThrowsWhenStudentNameIsBlank() {
        Notification n = validRegisterNotification();
        n.setStudentName("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendRegisterSuccess(n));

        Assertions.assertEquals("Nombre obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendRegisterSuccess(any());
    }

    @Test
    void sendSimulationResultSuccess() {
        Notification n = validSimulationNotification();

        String result = useCase.sendSimulationResult(n);

        Assertions.assertEquals("Resultado enviado correctamente", result);
        verify(notificationGateway).sendSimulationResult(n);
    }

    @Test
    void sendSimulationResultThrowsWhenToIsNull() {
        Notification n = validSimulationNotification();
        n.setTo(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendSimulationResult(n));

        Assertions.assertEquals("Correo destino obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendSimulationResult(any());
    }

    @Test
    void sendSimulationResultThrowsWhenToIsBlank() {
        Notification n = validSimulationNotification();
        n.setTo("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendSimulationResult(n));

        Assertions.assertEquals("Correo destino obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendSimulationResult(any());
    }

    @Test
    void sendSimulationResultThrowsWhenStudentNameIsNull() {
        Notification n = validSimulationNotification();
        n.setStudentName(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendSimulationResult(n));

        Assertions.assertEquals("Nombre obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendSimulationResult(any());
    }

    @Test
    void sendSimulationResultThrowsWhenStudentNameIsBlank() {
        Notification n = validSimulationNotification();
        n.setStudentName("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendSimulationResult(n));

        Assertions.assertEquals("Nombre obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendSimulationResult(any());
    }

    @Test
    void sendSimulationResultThrowsWhenScoreIsNull() {
        Notification n = validSimulationNotification();
        n.setScore(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendSimulationResult(n));

        Assertions.assertEquals("Puntaje obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendSimulationResult(any());
    }

    @Test
    void sendSimulationResultThrowsWhenScoreIsBlank() {
        Notification n = validSimulationNotification();
        n.setScore("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendSimulationResult(n));

        Assertions.assertEquals("Puntaje obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendSimulationResult(any());
    }

    @Test
    void sendExamLinkSuccess() {
        Notification n = validExamLinkNotification();

        String result = useCase.sendExamLink(n);

        Assertions.assertEquals("Link simulacro enviado", result);
        verify(notificationGateway).sendExamLink(n);
    }

    @Test
    void sendExamLinkThrowsWhenToIsNull() {
        Notification n = validExamLinkNotification();
        n.setTo(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendExamLink(n));

        Assertions.assertEquals("Correo destino obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendExamLink(any());
    }

    @Test
    void sendExamLinkThrowsWhenToIsBlank() {
        Notification n = validExamLinkNotification();
        n.setTo("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendExamLink(n));

        Assertions.assertEquals("Correo destino obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendExamLink(any());
    }

    @Test
    void sendExamLinkThrowsWhenStudentNameIsNull() {
        Notification n = validExamLinkNotification();
        n.setStudentName(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendExamLink(n));

        Assertions.assertEquals("Nombre obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendExamLink(any());
    }

    @Test
    void sendExamLinkThrowsWhenStudentNameIsBlank() {
        Notification n = validExamLinkNotification();
        n.setStudentName("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendExamLink(n));

        Assertions.assertEquals("Nombre obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendExamLink(any());
    }

    @Test
    void sendExamLinkThrowsWhenExamLinkIsNull() {
        Notification n = validExamLinkNotification();
        n.setExamLink(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendExamLink(n));

        Assertions.assertEquals("Link del simulacro obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendExamLink(any());
    }

    @Test
    void sendExamLinkThrowsWhenExamLinkIsBlank() {
        Notification n = validExamLinkNotification();
        n.setExamLink("   ");

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> useCase.sendExamLink(n));

        Assertions.assertEquals("Link del simulacro obligatorio", ex.getMessage());
        verify(notificationGateway, never()).sendExamLink(any());
    }
}
