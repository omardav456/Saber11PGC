package com.saber11.notificationemail.domain.model;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Notification {

    private String to;
    private String studentName;
    private String score;
    private String examLink;
    private String pdfPath;
    private String platformLink;
}