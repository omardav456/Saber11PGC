package com.saber11.notificationemail.domain.usecase;

import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;

public class NotificationUseCase {

    private final NotificationGateway notificationGateway;

    public NotificationUseCase(
            NotificationGateway notificationGateway
    ) {
        this.notificationGateway = notificationGateway;
    }

    public String sendEmail(Notification notification){

        if(notification.getTo() == null ||
                notification.getTo().isBlank()){

            throw new RuntimeException(
                    "Correo destino obligatorio"
            );
        }

        if(notification.getStudentName() == null ||
                notification.getStudentName().isBlank()){

            throw new RuntimeException(
                    "Nombre obligatorio"
            );
        }

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

        if(notification.getTo() == null ||
                notification.getTo().isBlank()){

            throw new RuntimeException(
                    "Correo destino obligatorio"
            );
        }

        if(notification.getStudentName() == null ||
                notification.getStudentName().isBlank()){

            throw new RuntimeException(
                    "Nombre obligatorio"
            );
        }

        notificationGateway.sendRegisterSuccess(notification);

        return "Correo de registro enviado";
    }


    public String sendSimulationResult(Notification notification){

        if(notification.getTo() == null ||
                notification.getTo().isBlank()){

            throw new RuntimeException(
                    "Correo destino obligatorio"
            );
        }

        if(notification.getStudentName() == null ||
                notification.getStudentName().isBlank()){

            throw new RuntimeException(
                    "Nombre obligatorio"
            );
        }

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

        if(notification.getTo() == null ||
                notification.getTo().isBlank()){

            throw new RuntimeException(
                    "Correo destino obligatorio"
            );
        }

        if(notification.getStudentName() == null ||
                notification.getStudentName().isBlank()){

            throw new RuntimeException(
                    "Nombre obligatorio"
            );
        }

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
