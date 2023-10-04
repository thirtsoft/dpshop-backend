package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.NotificationDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/notifications")
public interface NotificationApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Notification",
            notes = "Cette méthode permet d'ajouter une Notification", response = NotificationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Notification a été crée"),
            @ApiResponse(code = 400, message = "Aucune Notification  crée / modifié")

    })
    ResponseEntity<NotificationDto> save(@RequestBody NotificationDto notificationDto);

    @PostMapping(value = "/create-notification-to-article",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Notification pour un article donnée",
            notes = "Cette méthode permet d'ajouter une Notification pour un article donnée",
            response = NotificationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Notification a été crée"),
            @ApiResponse(code = 400, message = "Aucune Notification  crée / modifié")

    })
    ResponseEntity<NotificationDto> saveNotificationToArticle(@RequestParam("id") Long id, @RequestBody NotificationDto notificationDto);

    @PostMapping(value = "/create-rating-to-article", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Notification pour un article",
            notes = "Cette méthode permet d'attribuer une note à un article", response = NotificationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Notification a été crée"),
            @ApiResponse(code = 400, message = "Aucune Notification  crée / modifié")

    })
    ResponseEntity<NotificationDto> saveRating(@RequestBody NotificationDto notificationDto, @RequestParam("reference") String reference, @RequestParam Long id);

    @GetMapping(value = "/search-top3-rating-order-by-createdDateDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 3 dernières Notification",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 3 dernières Notification", responseContainer = "List<NotificationDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Notification / une liste vide")
    })
    ResponseEntity<List<NotificationDto>> getTop3ByOrderByCreatedDateDesc();

    @GetMapping(value = "/count-number-of-notification", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Notification",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Notification")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Notification / le nombre est nulle")
    })
    BigDecimal countNumberOfNotification();

    @GetMapping(value = "/count-number-of-notification-by-productId/{idProd}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Notification d'un produit",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Notification pour un produit donné")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Notification du produit / le nombre est nulle")
    })
    BigDecimal countNumberOfNotificationByProductId(@PathVariable("idProd") String prodRef);

    @GetMapping(value = "/search-top4-rating-order-by-createdDateDesc-by-productId/{idProd}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 4 dernières Notification d'un produit",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 4 dernières Notification d'un produit", responseContainer = "List<NotificationDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des 4 dernières Notification d'un produit / une liste vide")
    })
    ResponseEntity<List<NotificationDto>> getTop4ByOrderByCreatedDateDescByProductId(@PathVariable("idProd") String prodRef);

    @GetMapping(value = "/search-all-active-notifications", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des notifications actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des notifications actives",
            responseContainer = "List<NewsletterDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des notifications par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<NotificationDto>> getAllActiveNotifications();

    @DeleteMapping(value = "/delete-notification/{idNotification}")
    @ApiOperation(value = "Supprimer une notification par son ID",
            notes = "Cette méthode permet de supprimer une notification par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La notification a été supprimé")
    })
    void deleteNotification(@PathVariable("idNotification") Long idNotification);
}
