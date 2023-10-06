package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.FournisseurDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/fournisseurs")
public interface FournisseurApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Fournisseur",
            notes = "Cette méthode permet d'ajouter un Fournisseur", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Fournisseur a été crée"),
            @ApiResponse(code = 400, message = "Aucun Fournisseur  crée / modifié")
    })
    ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto fournisseurDto);

    @PutMapping(value = "/update/{idFournisseur}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Fournisseur par son ID",
            notes = "Cette méthode permet de modifier un Fournisseur par son ID", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Fournisseur a été modifiée"),
            @ApiResponse(code = 400, message = "Le Fournisseur a n'est pas modifiée")
    })
    ResponseEntity<FournisseurDto> update(@PathVariable("idFournisseur") Long id, @RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = "/findById/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Fournisseur par ID",
            notes = "Cette méthode permet de chercher une Fournisseur par son ID", response = FournisseurDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Fournisseur a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Fournisseur n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<FournisseurDto> findById(@PathVariable("idFournisseur") Long id);

    @GetMapping(value = "/count-number-of-fournisseurs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Fournisseur",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Fournisseur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Fournisseur / le nombre est nulle")
    })
    BigDecimal countNumberOfFournisseurs();

    @GetMapping(value = "/search-all-active-fournisseurs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des fournisseurs actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des fournisseurs actives",
            responseContainer = "List<FournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des fournisseurs par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<FournisseurDto>> getAllActiveFournisseurs();

    @DeleteMapping(value = "/delete-fournisseur/{idFournisseur}")
    @ApiOperation(value = "Supprimer une fournisseur par son ID",
            notes = "Cette méthode permet de supprimer une email par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La fournisseur a été supprimé")
    })
    void deleteFournisseur(@PathVariable("idFournisseur") Long idFournisseur);
}
