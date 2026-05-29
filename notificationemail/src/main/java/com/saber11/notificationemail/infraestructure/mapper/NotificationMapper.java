package com.saber11.notificationemail.infraestructure.mapper;

import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.infraestructure.dto.NotificationRequest;
import org.springframework.stereotype.Component;

@Component

public class NotificationMapper {

    public Notification toNotification(

            NotificationRequest notificationRequest) {

        return new Notification(

                notificationRequest.getTo(),
                notificationRequest.getStudentName(),
                notificationRequest.getScore(),
                notificationRequest.getExamLink(),
                notificationRequest.getPdfPath(),
                notificationRequest.getPlatformLink()
        );
    }
}