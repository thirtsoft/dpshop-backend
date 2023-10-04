package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.NotificationDto;

import java.math.BigDecimal;
import java.util.List;

public interface NotificationService {

    NotificationDto save(NotificationDto notificationDto);

    NotificationDto saveNotificationToArticle(Long id, NotificationDto notificationDto);

    List<NotificationDto> findTop3RatingOrderByCreatedDateDesc();

    BigDecimal countNumberOfNotification();

    BigDecimal countNumberOfNotificationByProductId(String prodRef);

    List<NotificationDto> findTop4ByOrderByCreatedDateDescByProductId(String prodRef);

    List<NotificationDto> findAllActiveNotifications();

    void deleteNotification(Long notificationId);

}
