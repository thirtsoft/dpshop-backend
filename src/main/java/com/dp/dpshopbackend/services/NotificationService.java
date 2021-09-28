package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.NotificationDto;

import java.math.BigDecimal;
import java.util.List;

public interface NotificationService {

    NotificationDto save(NotificationDto notificationDto);

    NotificationDto saveNotificationToArticle(Long id, NotificationDto notificationDto);

    NotificationDto update(Long idNote, NotificationDto notificationDto);

    NotificationDto findById(Long id);

    List<NotificationDto> findAll();

    List<NotificationDto> findTop3RatingOrderByCreatedDateDesc();

    BigDecimal countNumberOfNotification();

    void delete(Long id);
}
