package com.saber11.notificationemail.infraestructure.driver_adapters.smtp;
import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class NotificationGatewayImpl implements NotificationGateway {
    private final JavaMailSender javaMailSender;
    @Override
    public void sendEmail(Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notification.getTo());
        message.setSubject(notification.getSubject());
        message.setText(notification.getBody());
        javaMailSender.send(message);
    }
}