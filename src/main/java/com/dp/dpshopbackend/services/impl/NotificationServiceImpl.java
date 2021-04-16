package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.NotificationDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Notification;
import com.dp.dpshopbackend.repository.NotificationRepository;
import com.dp.dpshopbackend.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {


    @Autowired
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public NotificationDto save(NotificationDto notificationDto) {

        return NotificationDto.fromEntityToDto(
                notificationRepository.save(
                        NotificationDto.fromDtoToEntity(notificationDto)
                )
        );
    }

    @Override
    public NotificationDto findById(Long id) {
        if (id == null) {
            log.error("Notification Id is null");
            return null;
        }

        Optional<Notification> notification = notificationRepository.findById(id);

        return Optional.of(NotificationDto.fromEntityToDto(notification.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Notification avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<NotificationDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(NotificationDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Notification Id is null");
            return;
        }

        notificationRepository.deleteById(id);

    }
}
