package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    private Long id;

    @NotNull(message = "Le nombre d'étoile ne doit pas etre vide")
    @NotEmpty(message = "Le nombre d'étoile ne doit pas etre vide")
    @NotBlank(message = "Le nombre d'étoile ne doit pas etre vide")
    private float nbreEtoile;

    @NotNull(message = "Le message ne doit pas etre vide")
    @NotEmpty(message = "Le message ne doit pas etre vide")
    @NotBlank(message = "Le message ne doit pas etre vide")
    private String observation;

    private Date createdDate;

    @NotNull(message = "L'article ne doit pas etre vide")
    @NotEmpty(message = "L'article ne doit pas etre vide")
    @NotBlank(message = "L'article ne doit pas etre vide")
    private ArticleDto articleDto;

    private UtilisateurDto utilisateurDto;

    private int actif;

    public void setActif(boolean actif) {
        if (actif == true)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif() {
        if (actif == 1)
            return true;
        else
            return false;
    }

    public NotificationDto(Long id, float nbreEtoile, String observation, ArticleDto articleDto) {
        this.id = id;
        this.nbreEtoile = nbreEtoile;
        this.observation = observation;
        this.createdDate = new Date();
        this.articleDto = articleDto;
    }

    public static NotificationDto fromEntityToDto(Notification notification) {
        if (notification == null) {
            return null;
        }

        return NotificationDto.builder()
                .id(notification.getId())
                .nbreEtoile(notification.getNbreEtoile())
                .observation(notification.getObservation())
                .createdDate(notification.getCreatedDate())
                .articleDto(ArticleDto.fromEntityToDto(notification.getArticle()))
                .utilisateurDto(UtilisateurDto.fromEntityToDto(notification.getUtilisateur()))
                .actif(notification.getActif())
                .build();
    }

    public static Notification fromDtoToEntity(NotificationDto notificationDto) {
        if (notificationDto == null) {
            return null;
        }

        Notification notification = new Notification();
        notification.setId(notificationDto.getId());
        notification.setNbreEtoile(notificationDto.getNbreEtoile());
        notification.setObservation(notificationDto.getObservation());
        notification.setCreatedDate(notificationDto.getCreatedDate());
        notification.setArticle(ArticleDto.fromDtoToEntity(notificationDto.getArticleDto()));
        notification.setUtilisateur(UtilisateurDto.fromDtoToEntity(notificationDto.getUtilisateurDto()));
        notification.setActif(notificationDto.isActif());
        return notification;
    }



}
