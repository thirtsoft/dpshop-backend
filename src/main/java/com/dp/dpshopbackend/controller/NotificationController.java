package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.NotificationApi;
import com.dp.dpshopbackend.dto.NotificationDto;
import com.dp.dpshopbackend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController implements NotificationApi {

    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public ResponseEntity<NotificationDto> save(NotificationDto notificationDto) {
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
