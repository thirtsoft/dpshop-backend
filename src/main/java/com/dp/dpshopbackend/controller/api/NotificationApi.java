package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.NotificationDto;
import com.dp.dpshopbackend.models.Mail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface NotificationApi {

    @PostMapping(value = APP_ROOT + "/notifications/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Notification",
            notes = "Cette méthode permet d'ajouter une Notification", response = NotificationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Notification a été crée"),
            @ApiResponse(code = 400, message = "Aucune Notification  crée / modifié")

    })
    ResponseEntity<NotificationDto> save(@RequestBody NotificationDto notificationDto);

    @PostMapping(value = APP_ROOT + "/notifications/createNotificationToArticle",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Notification pour un article donnée",
            notes = "Cette méthode permet d'ajouter une Notification pour un article donnée",
            response = NotificationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Notification a été crée"),
            @ApiResponse(code = 400, message = "Aucune Notification  crée / modifié")

    })
    ResponseEntity<NotificationDto> saveNotificationToArticle(@RequestParam("id") Long id, @RequestBody NotificationDto notificationDto);

    @PostMapping(value = APP_ROOT + "/notifications/createRatingToArticle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Notification pour un article",
            notes = "Cette méthode permet d'attribuer une note à un article", response = NotificationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Notification a été crée"),
            @ApiResponse(code = 400, message = "Aucune Notification  crée / modifié")

    })
    ResponseEntity<NotificationDto> saveRating(@RequestBody NotificationDto notificationDto, @RequestParam("reference") String reference, @RequestParam Long id);

    @PutMapping(value = APP_ROOT + "/notifications/update/{idNote}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Commande par son ID",
            notes = "Cette méthode permet de modifier une Notification par son ID", response = NotificationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Notification a été modifiée"),
            @ApiResponse(code = 400, message = "La Notification a n'est pas modifiée")
    })
    ResponseEntity<NotificationDto> update(@PathVariable("idNote") Long id, @RequestBody NotificationDto notificationDto);


    @GetMapping(value = APP_ROOT + "/notifications/{idNotification}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Notification par ID",
            notes = "Cette méthode permet de chercher une Notification par son ID", response = NotificationDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Notification a été trouver"),
            @ApiResponse(code = 404, message = "Aucune Notification n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<NotificationDto> findById(@PathVariable("idNotification") Long id);

    @GetMapping(value = APP_ROOT + "/notifications/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Notification",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Notification", responseContainer = "List<NotificationDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Notification / une liste vide")
    })
    ResponseEntity<List<NotificationDto>> findAll();

    @GetMapping(value = APP_ROOT + "/notifications/searchAllNotificationsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par ordre descroissante",
            responseContainer = "List<NotificationDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<NotificationDto> > getAllNotificationsOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/notifications/searchTop3RatingOrderByCreatedDateDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 3 dernières Notification",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 3 dernières Notification", responseContainer = "List<NotificationDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Notification / une liste vide")
    })
    ResponseEntity<List<NotificationDto>> getTop3ByOrderByCreatedDateDesc();

    @GetMapping(value = APP_ROOT + "/notifications/countNumberOfNotification", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Notification",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Notification")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Notification / le nombre est nulle")
    })
    BigDecimal countNumberOfNotification();

    @DeleteMapping(value = APP_ROOT + "/notifications/delete/{idNotification}")
    @ApiOperation(value = "Supprimer une Notification par son ID",
            notes = "Cette méthode permet de supprimer une Notification par son ID", response = NotificationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Notification a été supprimé")
    })
    void delete(@PathVariable("idNotification") Long id);
}
