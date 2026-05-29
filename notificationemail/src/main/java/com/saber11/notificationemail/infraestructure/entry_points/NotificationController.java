package com.saber11.notificationemail.infraestructure.entry_points;

import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.usecase.NotificationUseCase;

import com.saber11.notificationemail.infraestructure.dto.NotificationRequest;
import com.saber11.notificationemail.infraestructure.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saber11/notificationemail")
@RequiredArgsConstructor

public class NotificationController {

    private final NotificationUseCase notificationUseCase;
    private final NotificationMapper notificationMapper;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestBody NotificationRequest request
    ){

        Notification notification =
                notificationMapper.toNotification(request);

        String response =
                notificationUseCase.sendEmail(notification);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    @PostMapping("/register")
    public ResponseEntity<String> sendRegisterSuccess(
            @RequestBody NotificationRequest request
    ){

        Notification notification =
                notificationMapper.toNotification(request);

        String response =
                notificationUseCase.sendRegisterSuccess(notification);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }


    @PostMapping("/result")
    public ResponseEntity<String> sendSimulationResult(
            @RequestBody NotificationRequest request
    ){

        Notification notification =
                notificationMapper.toNotification(request);

        String response =
                notificationUseCase.sendSimulationResult(notification);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }


    @PostMapping("/link")
    public ResponseEntity<String> sendExamLink(
            @RequestBody NotificationRequest request
    ){

        Notification notification =
                notificationMapper.toNotification(request);

        String response =
                notificationUseCase.sendExamLink(notification);

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}

