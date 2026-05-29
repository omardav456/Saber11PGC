package com.saber11.notificationemail.infraestructure.dto;

import lombok.Data;

@Data

public class NotificationRequest {

    private String to;
    private String studentName;
    private String score;
    private String examLink;
    private String pdfPath;
    private String platformLink;
}
