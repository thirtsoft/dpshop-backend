package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/emails")
public interface EmailApi {

    @PostMapping(value = "/sendEmail")
    @ApiOperation(value = "Envoyer un email",
            notes = "Cette méthode permet d'envoyer un email",
            response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<EmailDto> sendEmail(@RequestBody EmailDto emailDto) throws MailException;

    @PostMapping(value = "/sendToFournisseur")
    @ApiOperation(value = "Envoyer un email à un Fournisseurs",
            notes = "Cette méthode permet d'envoyer un email à un Fournisseurs",
            response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<FournisseurDto> sendMailToFournisseur(@RequestBody FournisseurDto fournisseurDto) throws MailException;

    @PostMapping(value = "/sendToNewsletter")
    @ApiOperation(value = "Envoyer un email à un client",
            notes = "Cette méthode permet d'envoyer un email à un client",
            response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<NewsletterDto> sendMailToCustomer(@RequestBody NewsletterDto newsletterDto) throws MailException;

    @PostMapping(value = "/sendMailToAllCustomers")
    @ApiOperation(value = "Envoyer un email à plusieurs Clients",
            notes = "Cette méthode permet d'envoyer un email à plusieurs Clients",
            response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<NewsletterDto> sendMailToAllCustomers(@RequestBody NewsletterDto newsletterDto) throws MailException;

    @PostMapping(value = "/sendMailToManager")
    @ApiOperation(value = "Envoyer un email au Manager du site",
            notes = "Cette méthode permet d'envoyer un email au Manager du site",
            response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<EmailDto> sendEmailToManager(@RequestBody EmailDto emailDto) throws MailException;

    @GetMapping(value = "/findById/{idEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Email par ID",
            notes = "Cette méthode permet de chercher un Email par son ID", response = ClientDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Email a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Email n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<EmailDto> getEmailById(@PathVariable("idEmail") Long id);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Email",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Email", responseContainer = "List<EmailDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Email / une liste vide")
    })
    ResponseEntity<List<EmailDto>> getAll();

    @GetMapping(value = "/searchAllEmailsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Emails par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Emails par ordre descroissante",
            responseContainer = "List<EmailDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Emails  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<EmailDto>> getAllNewsletterOrderByIdDesc();

    @GetMapping(value = "/countNumberOfEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Email dans le moi",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Email / le nombre est nulle")
    })
    BigDecimal countNumberOfEmail();

    @DeleteMapping(value = "/delete/{idEmail}")
    @ApiOperation(value = "Supprimer un Email par son ID",
            notes = "Cette méthode permet de supprimer une Email par son ID", response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Notification a été supprimé")
    })
    void delete(@PathVariable("idEmail") Long id);

    @GetMapping(value = APP_ROOT + "/emails/search-all-active-emails", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des emails actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des emails actives",
            responseContainer = "List<EmailDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des emails par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<EmailDto>> getAllActiveEmails();

    @DeleteMapping(value = APP_ROOT + "/emails/delete-email/{idEmail}")
    @ApiOperation(value = "Supprimer une email par son ID",
            notes = "Cette méthode permet de supprimer une email par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La countries a été supprimé")
    })
    void deleteEmail(@PathVariable("idEmail") Long idEmail);

}
