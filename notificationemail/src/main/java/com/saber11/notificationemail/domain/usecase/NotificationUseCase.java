package com.saber11.notificationemail.domain.usecase;

import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;

import java.util.regex.Pattern;

public class NotificationUseCase {

    private final NotificationGateway notificationGateway;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public NotificationUseCase(
            NotificationGateway notificationGateway
    ) {
        this.notificationGateway = notificationGateway;
    }

    private void validateEmail(String to) {
        if (to == null || to.isBlank()) {
            throw new RuntimeException("Correo destino obligatorio");
        }
        if (!EMAIL_PATTERN.matcher(to.trim()).matches()) {
            throw new RuntimeException("Formato de correo inválido");
        }
    }

    private void validateName(String studentName) {
        if (studentName == null || studentName.isBlank()) {
            throw new RuntimeException("Nombre obligatorio");
        }
    }

    public String sendEmail(Notification notification){

        validateEmail(notification.getTo());
        validateName(notification.getStudentName());

        if(notification.getPlatformLink() == null ||
                notification.getPlatformLink().isBlank()){

            throw new RuntimeException(
                    "Link plataforma obligatorio"
            );
        }

        notificationGateway.sendEmail(notification);

        return "Correo de bienvenida enviado";
    }


    public String sendRegisterSuccess(Notification notification){

        validateEmail(notification.getTo());
        validateName(notification.getStudentName());

        notificationGateway.sendRegisterSuccess(notification);

        return "Correo de registro enviado";
    }


    public String sendSimulationResult(Notification notification){

        validateEmail(notification.getTo());
        validateName(notification.getStudentName());

        if(notification.getScore() == null ||
                notification.getScore().isBlank()){

            throw new RuntimeException(
                    "Puntaje obligatorio"
            );
        }

        notificationGateway.sendSimulationResult(notification);

        return "Resultado enviado correctamente";
    }

    public String sendExamLink(Notification notification){

        validateEmail(notification.getTo());
        validateName(notification.getStudentName());

        if(notification.getExamLink() == null ||
                notification.getExamLink().isBlank()){

            throw new RuntimeException(
                    "Link del simulacro obligatorio"
            );
        }

        notificationGateway.sendExamLink(notification);

        return "Link simulacro enviado";
    }
}
