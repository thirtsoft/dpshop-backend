package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.ClientDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/addresslivraisons")
public interface AddressLivraisonApi {

    @GetMapping(value = "/{idAddressLivraison}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une AddressLivraison par ID",
            notes = "Cette méthode permet de chercher une AddressLivraison par son ID", response = AddressLivraisonDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'AddressLivraison a été trouver"),
            @ApiResponse(code = 404, message = "Aucune AddressLivraison n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<AddressLivraisonDto> findById(@PathVariable("idAddressLivraison") Long id);

    @GetMapping(value = "/search-all-active-addresslivraisons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des AddressLivraisons actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des AddressLivraisons actives",
            responseContainer = "List<AddressLivraisonDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des AddressLivraisons par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<AddressLivraisonDto>> getAllActiveAddressLivraisons();

    @DeleteMapping(value = "/delete-addresslivraisons/{idAddressLivraison}")
    @ApiOperation(value = "Supprimer une Addresse de livraison par son ID",
            notes = "Cette méthode permet de supprimer une Addresse de livraison  par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Article a été supprimé")
    })
    void deleteAddressLivraison(@PathVariable("idAddressLivraison") Long idAddressLivraison);
}
