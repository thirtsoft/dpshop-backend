package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.NotificationDto;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.models.Notification;
import com.dp.dpshopbackend.repository.NotificationRepository;
import com.dp.dpshopbackend.services.impl.NotificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Test
    public void CreateNotificationTest() {
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .username("username")
                .password("passer123")
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .build();
        NotificationDto notificationDto = NotificationDto.builder()
                .id(1L)
                .reference("ref")
                .nbreEtoile("4etoile")
                .observation("bon")
                .articleDto(articleDto)
                .utilisateurDto(utilisateurDto)
                .build();
        Notification notification = NotificationDto.fromDtoToEntity(notificationDto);
        when(notificationRepository.save(notification)).thenReturn(notification);

        NotificationDto notificationDtoSavedResult = notificationService.save(notificationDto);

        verify(notificationRepository).save(notification);
        assertThat(notificationDto).isNotNull();
        assertThat(notificationDtoSavedResult).isEqualTo(notificationDto);
        assertThat(notificationDtoSavedResult.getId()).isEqualTo(notification.getId());
        assertThat(notificationDtoSavedResult.getReference()).isEqualTo(notification.getReference());
        assertThat(notificationDtoSavedResult.getNbreEtoile()).isEqualTo(notification.getNbreEtoile());
    }

    @Test
    public void findAllTest() {
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .username("username")
                .password("passer123")
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .build();
        NotificationDto notificationDto = NotificationDto.builder()
                .id(1L)
                .reference("ref")
                .nbreEtoile("4etoile")
                .observation("bon")
                .articleDto(articleDto)
                .utilisateurDto(utilisateurDto)
                .build();
        Notification notification = NotificationDto.fromDtoToEntity(notificationDto);
        when(notificationRepository.findAll()).thenReturn(singletonList(notification));

        List<NotificationDto> notificationDtoList = notificationService.findAll();

        assertThat(notificationDtoList).isNotNull();
        assertThat(notificationDtoList.size()).isEqualTo(1);
        verify(notificationRepository).findAll();
        assertThat(notificationDtoList.get(0)).isEqualTo(NotificationDto.fromEntityToDto(notification));
    }

    @Test
    public void findByIdTest() {
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .username("username")
                .password("passer123")
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .build();
        NotificationDto notificationDto = NotificationDto.builder()
                .id(1L)
                .reference("ref")
                .nbreEtoile("4etoile")
                .observation("bon")
                .articleDto(articleDto)
                .utilisateurDto(utilisateurDto)
                .build();
        Optional<Notification> notification = Optional.ofNullable(NotificationDto.fromDtoToEntity(notificationDto));
        when(notificationRepository.findById(notification.get().getId())).thenReturn(notification);

        NotificationDto notificationDtoSavedResult = notificationService.findById(notificationDto.getId());

        verify(notificationRepository).findById(notification.get().getId());
        assertThat(notificationDto).isNotNull();
        assertThat(notificationDtoSavedResult).isEqualTo(notificationDto);
        assertThat(notificationDtoSavedResult.getId()).isEqualTo(notification.get().getId());

    }

}
