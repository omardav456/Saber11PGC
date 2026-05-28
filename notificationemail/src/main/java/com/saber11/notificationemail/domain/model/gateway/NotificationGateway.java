package com.saber11.notificationemail.domain.model.gateway;

import com.saber11.notificationemail.domain.model.Notification;

public interface NotificationGateway {
    void sendEmail(Notification notification);

}
