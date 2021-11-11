package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.LigneCommandeDto;
import com.dp.dpshopbackend.models.Mail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface MailApi {

    @PostMapping(value = APP_ROOT + "/mails/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Envoyer un email et Enregistrer l'email",
            notes = "Cette méthode permet à un client d'envoyer un email au gestionnaire de la boutique", response = Mail.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'email  a été envoyer"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyer / modifié")
    })
    void save(@RequestBody Mail mail);

    @GetMapping(value = APP_ROOT + "/mails/{idMail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une email par ID",
            notes = "Cette méthode permet de chercher une email par son ID", response = Mail.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La email a été trouver"),
            @ApiResponse(code = 404, message = "Aucun email n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<Mail> findById(@PathVariable("idMail") Long id);

    @GetMapping(value = APP_ROOT + "/mails/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des emails",
            notes = "Cette méthode permet de chercher et renvoyer la liste des emails", responseContainer = "List<Mail>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des emails / une liste vide")
    })
    ResponseEntity<List<Mail>> findAll();

    @GetMapping(value = APP_ROOT + "/articles/searchAllMailOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par ordre descroissante",
            responseContainer = "List<Mail>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<Mail> > getAllMailsOrderByIdDesc();

    @DeleteMapping(value = APP_ROOT + "/mails/delete/{idMail}")
    @ApiOperation(value = "Supprimer un emails par son ID",
            notes = "Cette méthode permet de supprimer une emails par son ID", response = Mail.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Country a été supprimé")
    })
    void delete(@PathVariable("idMail") Long id);
}
