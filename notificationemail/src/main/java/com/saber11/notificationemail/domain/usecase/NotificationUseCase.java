package com.saber11.notificationemail.domain.usecase;
import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class NotificationUseCase {
    private final NotificationGateway notificationGateway;

    public String sendEmail(Notification notification){

        if(notification.getTo() == null || notification.getTo().isBlank()){

            throw new RuntimeException("Correo destino obligatorio"); }

        if(notification.getSubject() == null || notification.getSubject().isBlank()){

            throw new RuntimeException("Asunto obligatorio"); }

        if(notification.getBody() == null || notification.getBody().isBlank()){

            throw new RuntimeException("Mensaje obligatorio"); } notificationGateway.sendEmail(notification);

        return "Correo enviado correctamente";
    }
}