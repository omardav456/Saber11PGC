package com.saber11.notificationemail.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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