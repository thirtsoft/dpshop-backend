package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.NotificationApi;
import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.NotificationDto;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.services.ArticleService;
import com.dp.dpshopbackend.services.NotificationService;
import com.dp.dpshopbackend.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://soulbusinesse.com")
@RestController
public class NotificationController implements NotificationApi {

    private final NotificationService notificationService;

    private final ArticleService articleService;

    private final UtilisateurService utilisateurService;

    @Autowired
    public NotificationController(NotificationService notificationService,
                                  ArticleService articleService,
                                  UtilisateurService utilisateurService) {
        this.notificationService = notificationService;
        this.articleService = articleService;
        this.utilisateurService = utilisateurService;
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
    public ResponseEntity<NotificationDto> saveRating(NotificationDto notificationDto, String reference, Long id) {

        Article article = Optional.of(ArticleDto.fromDtoToEntity(articleService.findByReference(reference))).get();

        Utilisateur utilisateur = Optional.of(UtilisateurDto.fromDtoToEntity(utilisateurService.findById(id))).get();

        notificationDto.setArticleDto(ArticleDto.fromEntityToDto(article));
        notificationDto.setUtilisateurDto(UtilisateurDto.fromEntityToDto(utilisateur));

        notificationDto.setCreatedDate(new Date());

        NotificationDto notificationDtoResult = notificationService.save(notificationDto);

        return new ResponseEntity<>(notificationDtoResult, HttpStatus.CREATED);
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
    public ResponseEntity<List<NotificationDto>> findAll() {
        List<NotificationDto> notificationDtoList = notificationService.findAll();
        return new ResponseEntity<>(notificationDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<NotificationDto>> getAllNotificationsOrderByIdDesc() {
        List<NotificationDto> notificationDtoList = notificationService.findByOrderByIdDesc();
        return new ResponseEntity<>(notificationDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<NotificationDto>> getTop3ByOrderByCreatedDateDesc() {
        List<NotificationDto> notificationDtoList = notificationService.findTop3RatingOrderByCreatedDateDesc();
        return new ResponseEntity<>(notificationDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfNotification() {
        return notificationService.countNumberOfNotification();
    }

    @Override
    public BigDecimal countNumberOfNotificationByProductId(String prodRef) {
        return notificationService.countNumberOfNotificationByProductId(prodRef);
    }

    @Override
    public ResponseEntity<List<NotificationDto>> getTop4ByOrderByCreatedDateDescByProductId(String prodRef) {
        List<NotificationDto> notificationDtoList = notificationService.findTop4ByOrderByCreatedDateDescByProductId(prodRef);
        return new ResponseEntity<>(notificationDtoList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        notificationService.delete(id);
    }

    @Override
    public ResponseEntity<List<NotificationDto>> getAllActiveNotifications() {
        List<NotificationDto> notificationDtoList = notificationService.findAllActiveNotifications();
        return new ResponseEntity<>(notificationDtoList, HttpStatus.OK);
    }

    @Override
    public void deleteNotification(Long idNotification) {
        notificationService.deleteNotification(idNotification);
    }
}
