package com.saber11.notificationemail.infraestructure.driver_adapters.smtp;
import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;

import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Repository
@RequiredArgsConstructor

public class NotificationGatewayImpl implements NotificationGateway {
    private final JavaMailSender javaMailSender;

    @Override

    public void sendEmail(Notification notification) {
        try{

            MimeMessage mimeMessage =
                javaMailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            mimeMessage,
                            true,
                            "UTF-8"
                    );

            helper.setTo(notification.getTo());

            helper.setSubject(
                    "Bienvenido a la plataforma Saber 11"
            );

            String html =
                    loadTemplate("welcome.html");

            html = html.replace(
                    "{{name}}",
                    notification.getStudentName()
            );

            html = html.replace(
                    "{{platformLink}}",
                    notification.getPlatformLink()
            );

            helper.setText(html, true);

            javaMailSender.send(mimeMessage);

        } catch (Exception e){

            e.printStackTrace();

            throw new RuntimeException(
                    "Error enviando correo: " + e.getMessage()
            );
        }
    }

    @Override
    public void sendRegisterSuccess(Notification notification) {

        try {

            MimeMessage mimeMessage =
                    javaMailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            mimeMessage,
                            true,
                            "UTF-8"
                    );

            helper.setTo(notification.getTo());

            helper.setSubject(
                    "Registro exitoso"
            );

            String html =
                    loadTemplate("register.html");

            html = html.replace(
                    "{{name}}",
                    notification.getStudentName()
            );

            helper.setText(html, true);

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Error cargando plantilla: " + e.getMessage()
            );
        }
    }

    @Override
    public void sendSimulationResult(Notification notification) {

        try {

            MimeMessage mimeMessage =
                    javaMailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            mimeMessage,
                            true,
                            "UTF-8"
                    );

            helper.setTo(notification.getTo());

            helper.setSubject(
                    "Resultado simulacro Saber 11"
            );

            String html =
                    loadTemplate("simulation.html");

            html = html.replace(
                    "{{name}}",
                    notification.getStudentName()
            );

            html = html.replace(
                    "{{score}}",
                    notification.getScore()
            );

            helper.setText(html, true);

            if(notification.getPdfPath() != null &&
                    !notification.getPdfPath().isBlank()) {

                helper.addAttachment(
                        "resultado-simulacro.pdf",
                        new File(notification.getPdfPath())
                );
            }

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    e.getMessage()
            );
        }
    }

    @Override
    public void sendExamLink(Notification notification) {

        try {

            MimeMessage mimeMessage =
                    javaMailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            mimeMessage,
                            true,
                            "UTF-8"
                    );

            helper.setTo(notification.getTo());

            helper.setSubject(
                    "Nuevo simulacro disponible"
            );

            String html =
                    loadTemplate("examen-link.html");

            html = html.replace(
                    "{{name}}",
                    notification.getStudentName()
            );

            html = html.replace(
                    "{{examLink}}",
                    notification.getExamLink()
            );

            helper.setText(html, true);

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    e.getMessage()
            );
        }
    }

    protected String loadTemplate(String templateName) {

        try {

            Resource resource =
                    new ClassPathResource(
                            "templates/" + templateName
                    );

            return new String(
                    resource.getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8
            );

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    e.getMessage()
            );
        }
    }
}