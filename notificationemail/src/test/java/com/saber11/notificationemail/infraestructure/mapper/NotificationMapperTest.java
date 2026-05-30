package com.saber11.notificationemail.infraestructure.mapper;

import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.infraestructure.dto.NotificationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotificationMapperTest {

    private final NotificationMapper mapper = new NotificationMapper();

    @Test
    void toNotificationMapsAllFields() {
        NotificationRequest request = new NotificationRequest();
        request.setTo("test@test.com");
        request.setStudentName("Juan");
        request.setScore("350");
        request.setExamLink("http://exam.com");
        request.setPdfPath("/path/to.pdf");
        request.setPlatformLink("http://saber11.com");

        Notification result = mapper.toNotification(request);

        Assertions.assertEquals("test@test.com", result.getTo());
        Assertions.assertEquals("Juan", result.getStudentName());
        Assertions.assertEquals("350", result.getScore());
        Assertions.assertEquals("http://exam.com", result.getExamLink());
        Assertions.assertEquals("/path/to.pdf", result.getPdfPath());
        Assertions.assertEquals("http://saber11.com", result.getPlatformLink());
    }
}
