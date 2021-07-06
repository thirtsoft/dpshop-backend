package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.CommandeDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface CommandeApi {

    @PostMapping(value = APP_ROOT + "/commandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Commande",
            notes = "Cette méthode permet d'ajouter une Commande", response = CommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Commande a été crée"),
            @ApiResponse(code = 400, message = "Aucune Commande  crée / modifié")

    })
    ResponseEntity<CommandeDto> save(@RequestBody CommandeDto commandeDto);

    @PutMapping(value = APP_ROOT + "/commandes/update/{idCommande}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Commande par son ID",
            notes = "Cette méthode permet de modifier une Commande par son ID", response = CommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Artilce a été modifiée"),
            @ApiResponse(code = 400, message = "L'Artilce a n'est pas modifiée")
    })
    ResponseEntity<CommandeDto> update(@PathVariable("idCommande") Long id, @RequestBody CommandeDto commandeDto);

    @GetMapping(value = APP_ROOT + "/commandes/{idCommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Commande par ID",
            notes = "Cette méthode permet de chercher une Commande par son ID", response = CommandeDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Commande a été trouver"),
            @ApiResponse(code = 404, message = "Aucune Commande n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<CommandeDto> findById(@PathVariable("idCommande") Long id);

    @GetMapping(value = APP_ROOT + "/commandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes / une liste vide")
    })
    List<CommandeDto> findAll();

    @GetMapping(value = APP_ROOT + "/commandes/countSumOfCommande", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Commande",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Commande")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Commande / le nombre est nulle")
    })
    BigDecimal countNumberOfCommande();

    @GetMapping(value = APP_ROOT + "/commandes/countSumOfCommandeByMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le montant de Commande du moi",
            notes = "Cette méthode permet de chercher et renvoyer le montant de Commande du moi encours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant des Commande par moi / somme nulle")
    })
    BigDecimal sumTotalOfCommandesByMonth();

    @GetMapping(value = APP_ROOT + "/commandes/searchCommandeByCustomerByPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commande par client",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commande par Client par pages", responseContainer = "Page<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commande par Client par pages / une liste vide")
    })
    Page<CommandeDto> getListCommandeByCustomerByPageables(@RequestParam("clientId") Long clientId, @RequestParam(name = "page") int page,
                                                           @RequestParam(name = "size") int size);

    @DeleteMapping(value = APP_ROOT + "/commandes/delete/{idCommande}")
    @ApiOperation(value = "Supprimer une Commande par son ID",
            notes = "Cette méthode permet de supprimer une Commande par son ID", response = CommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La CommandeDto a été supprimé")
    })
    void delete(@PathVariable("idCommande") Long id);
}
