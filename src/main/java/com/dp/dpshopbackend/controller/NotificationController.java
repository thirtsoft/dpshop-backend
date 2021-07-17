package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.NotificationApi;
import com.dp.dpshopbackend.dto.NotificationDto;
import com.dp.dpshopbackend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class NotificationController implements NotificationApi {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public ResponseEntity<NotificationDto> save(NotificationDto notificationDto) {
        return ResponseEntity.ok(notificationService.save(notificationDto));
    }

    @Override
    public ResponseEntity<NotificationDto> saveNotificationToArticle(Long id, NotificationDto notificationDto) {
        return ResponseEntity.ok(notificationService.saveNotificationToArticle(id, notificationDto));
    }

    @Override
    public ResponseEntity<NotificationDto> update(Long id, NotificationDto notificationDto) {
        notificationDto.setId(id);
        return ResponseEntity.ok(notificationService.save(notificationDto));
    }

    @Override
    public ResponseEntity<NotificationDto> findById(Long id) {
        return ResponseEntity.ok(notificationService.findById(id));
    }

    @Override
    public List<NotificationDto> findAll() {
        return notificationService.findAll();
    }

    @Override
    public void delete(Long id) {
        notificationService.delete(id);
    }
}
