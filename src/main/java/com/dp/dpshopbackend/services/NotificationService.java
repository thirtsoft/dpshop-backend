package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.NotificationDto;

import java.util.List;

public interface NotificationService {

    NotificationDto save(NotificationDto notificationDto);

    NotificationDto findById(Long id);

    //  ScategorieDto findByLibelle(String libelle);

    List<NotificationDto> findAll();

    void delete(Long id);
}
