package com.saber11.notificationemail.infraestructure.driver_adapters.smtp;
import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class NotificationGatewayImpl implements NotificationGateway {

    private final RestTemplate restTemplate;

    @Value("${mailtrap.api.token}")
    private String apiToken;

    @Value("${mailtrap.from.email}")
    private String fromEmail;

    @Value("${mailtrap.from.name}")
    private String fromName;

    @Value("${mailtrap.api.url}")
    private String apiUrl;

    @Override
    public void sendEmail(Notification notification) {
        try {
            String html = loadTemplate("welcome.html");
            html = html.replace("{{name}}", notification.getStudentName());
            html = html.replace("{{platformLink}}", notification.getPlatformLink());

            sendMailtrap(notification.getTo(), "Bienvenido a la plataforma Saber 11", html, null);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error enviando correo: " + e.getMessage());
        }
    }

    @Override
    public void sendRegisterSuccess(Notification notification) {
        try {
            String html = loadTemplate("register.html");
            html = html.replace("{{name}}", notification.getStudentName());

            sendMailtrap(notification.getTo(), "Registro exitoso", html, null);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error enviando correo: " + e.getMessage());
        }
    }

    @Override
    public void sendSimulationResult(Notification notification) {
        try {
            String html = loadTemplate("simulation.html");
            html = html.replace("{{name}}", notification.getStudentName());
            html = html.replace("{{score}}", notification.getScore());

            List<Map<String, Object>> attachments = null;
            if (notification.getPdfPath() != null && !notification.getPdfPath().isBlank()) {
                attachments = new ArrayList<>();
                File pdfFile = new File(notification.getPdfPath());
                String base64Content = Base64.getEncoder().encodeToString(Files.readAllBytes(pdfFile.toPath()));

                Map<String, Object> attachment = new HashMap<>();
                attachment.put("content", base64Content);
                attachment.put("type", "application/pdf");
                attachment.put("filename", "resultado-simulacro.pdf");
                attachments.add(attachment);
            }

            sendMailtrap(notification.getTo(), "Resultado simulacro Saber 11", html, attachments);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void sendExamLink(Notification notification) {
        try {
            String html = loadTemplate("examen-link.html");
            html = html.replace("{{name}}", notification.getStudentName());
            html = html.replace("{{examLink}}", notification.getExamLink());

            sendMailtrap(notification.getTo(), "Nuevo simulacro disponible", html, null);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private void sendMailtrap(String to, String subject, String html,
                              List<Map<String, Object>> attachments) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Api-Token", apiToken);

        Map<String, Object> from = new HashMap<>();
        from.put("email", fromEmail);
        from.put("name", fromName);

        Map<String, Object> recipient = new HashMap<>();
        recipient.put("email", to);

        Map<String, Object> body = new HashMap<>();
        body.put("from", from);
        body.put("to", Collections.singletonList(recipient));
        body.put("subject", subject);
        body.put("html", html);
        body.put("category", "Saber 11");

        if (attachments != null && !attachments.isEmpty()) {
            body.put("attachments", attachments);
        }

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Mailtrap API error: HTTP " + response.getStatusCode());
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
                    "Error cargando plantilla: " + e.getMessage()
            );
        }
    }
}
