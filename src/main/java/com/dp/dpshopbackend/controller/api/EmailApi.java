package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.dto.EmailDto;
import com.dp.dpshopbackend.dto.FournisseurDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface EmailApi {

    @PostMapping(value = APP_ROOT + "/emails/sendEmail")
    @ApiOperation(value = "Envoyer un email",
            notes = "Cette méthode permet d'envoyer un email",
            response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<EmailDto> sendEmail(@RequestBody EmailDto emailDto);


    @PostMapping(value = APP_ROOT + "/emails/sendMail")
    @ApiOperation(value = "Envoyer un email à un Fournisseurs",
            notes = "Cette méthode permet d'envoyer un email à un Fournisseurs",
            response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<FournisseurDto> sendMail(@RequestBody FournisseurDto fournisseurDto);

    @PostMapping(value = APP_ROOT + "/emails/sendMailToManager")
    @ApiOperation(value = "Envoyer un email au Manager du site",
            notes = "Cette méthode permet d'envoyer un email au Manager du site",
            response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<EmailDto> sendEmailToManager(@RequestBody EmailDto emailDto);

    @GetMapping(value = APP_ROOT + "/emails/findById/{idEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Email par ID",
            notes = "Cette méthode permet de chercher un Email par son ID", response = ClientDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Email a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Email n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<EmailDto> findById(@PathVariable("idEmail") Long id);

    @GetMapping(value = APP_ROOT + "/emails/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Email",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Email", responseContainer = "List<EmailDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Email / une liste vide")
    })
    ResponseEntity<List<EmailDto>> findAll();

    @GetMapping(value = APP_ROOT + "/emails/searchAllEmailssOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Emails par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Emails par ordre descroissante",
            responseContainer = "List<EmailDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Emails  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<EmailDto>> getAllClientsOrderByIdDesc();

}
