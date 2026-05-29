package com.saber11.notificationemail.infraestructure.dto;

import lombok.Data;

@Data

public class NotificationRequest {

    private String to;
    private String subject;
    private String body;
}
