package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.NewsletterDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/newsletters")
public interface NewsletterApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "S'inscrire au Newsletter",
            notes = "Cette méthode permet de s'inscrire au Newsletter", response = NewsletterDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Newsletter a été crée"),
            @ApiResponse(code = 400, message = "Aucun Newsletter  crée / modifié")
    })
    ResponseEntity<NewsletterDto> save(@RequestBody NewsletterDto newsletterDto);

    @GetMapping(value = "/count-number-of-newsletters", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Newsletter",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Newsletter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Newsletter / le nombre est nulle")
    })
    BigDecimal countNumberOfNewsletters();

    @GetMapping(value = "/search-all-active-newsletters", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des newsletters actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des newsletters actives",
            responseContainer = "List<NewsletterDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des newsletters par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<NewsletterDto>> getAllActiveNewsletters();

    @DeleteMapping(value = "/delete-newsletters/{idNewsletter}")
    @ApiOperation(value = "Supprimer une newsletter par son ID",
            notes = "Cette méthode permet de supprimer une newsletter par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La newsletter a été supprimé")
    })
    void deleteNewsletter(@PathVariable("idNewsletter") Long idNewsletter);
}
