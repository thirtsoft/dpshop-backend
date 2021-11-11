package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.dto.ClientDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface AddressLivraisonApi {

    @PostMapping(value = APP_ROOT + "/addresslivraisons/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une AddressLivraison",
            notes = "Cette méthode permet d'ajouter une AddressLivraison", response = AddressLivraisonDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'AddressLivraison a été crée"),
            @ApiResponse(code = 400, message = "Aucune AddressLivraison  crée / modifié")
    })
    ResponseEntity<AddressLivraisonDto> save(@RequestBody AddressLivraisonDto addressLivraisonDto);

    @PutMapping(value = APP_ROOT + "/addresslivraisons/update/{idAddress}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une AddressLivraison par son ID",
            notes = "Cette méthode permet de modifier un AddressLivraison par son ID", response = AddressLivraisonDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'AddressLivraison a été modifiée"),
            @ApiResponse(code = 400, message = "L'AddressLivraison a n'est pas modifiée")
    })
    ResponseEntity<AddressLivraisonDto> update(@PathVariable("idAddress") Long id, @RequestBody AddressLivraisonDto addressLivraisonDto);

    @GetMapping(value = APP_ROOT + "/addresslivraisons/{idAddressLivraison}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une AddressLivraison par ID",
            notes = "Cette méthode permet de chercher une AddressLivraison par son ID", response = AddressLivraisonDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'AddressLivraison a été trouver"),
            @ApiResponse(code = 404, message = "Aucune AddressLivraison n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<AddressLivraisonDto> findById(@PathVariable("idAddressLivraison") Long id);

    @GetMapping(value = APP_ROOT + "/addresslivraisons/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des AddressLivraisons",
            notes = "Cette méthode permet de chercher et renvoyer la liste des AddressLivraisons", responseContainer = "List<AddressLivraisonDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des AddressLivraisons / une liste vide")
    })
    List<AddressLivraisonDto> findAll();

    @GetMapping(value = APP_ROOT + "/addresslivraisons/searchAllAddressLivraisonsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des AddressLivraisons par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des AddressLivraisons par ordre descroissante",
            responseContainer = "List<AddressLivraisonDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des AddressLivraisons par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<AddressLivraisonDto>> getAllAddressLivraisonsOrderByIdDesc();

    @DeleteMapping(value = APP_ROOT + "/addresslivraisons/delete/{idAddressLivraison}")
    @ApiOperation(value = "Supprimer une AddressLivraison par son ID",
            notes = "Cette méthode permet de supprimer une AddressLivraison par son ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'AddressLivraison a été supprimé")
    })
    void delete(@PathVariable("idAddressLivraison") Long id);
}
