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

@RequestMapping(value = APP_ROOT + "/commandes")
public interface CommandeApi {

    @PatchMapping(value = "/update-status-of-commande/{id}")
    @ApiOperation(value = "Modifier une Commande par son Status",
            notes = "Cette méthode permet de modifier une Commande par son Status", response = CommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le status de la Commande a été modifié")
    })
    ResponseEntity<CommandeDto> updateStatusOfCommande(@RequestParam("status") String status, @PathVariable("id") String id);

    @GetMapping(value = "/findById/{idCommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Commande par ID",
            notes = "Cette méthode permet de chercher une Commande par son ID", response = CommandeDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Commande a été trouver"),
            @ApiResponse(code = 404, message = "Aucune Commande n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<CommandeDto> findById(@PathVariable("idCommande") Long id);

    @GetMapping(value = "/count-number-of-commande", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Commande",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Commande")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Commande / le nombre est nulle")
    })
    BigDecimal countNumberOfCommande();

    @GetMapping(value = "/count-number-of-orders-in-month", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Commande du mois",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Commande du mois")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Commande / le nombre est nulle")
    })
    BigDecimal countNumberOfOrdersInMonth();

    @GetMapping(value = "/count-number-of-orders-by-pending-status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Commande encours",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Commande encours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Commande / le nombre est nulle")
    })
    BigDecimal countNumberOfOrdersByStatusPending();

    @GetMapping(value = "/sum-total-of-commande-by-day", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le montant de Commande du jour",
            notes = "Cette méthode permet de chercher et renvoyer le montant de Commande du jour encours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant des Commande par jour / somme nulle")
    })
    BigDecimal sumTotaleOfCommandeByDay();

    @GetMapping(value = "/sum-total-of-commande-by-month", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le montant de Commande du moi",
            notes = "Cette méthode permet de chercher et renvoyer le montant de Commande du moi encours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant des Commande par moi / somme nulle")
    })
    BigDecimal sumTotaleOfCommandeByMonth();

    @GetMapping(value = "/sum-total-of-commande-by-year", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le montant de Commande d'une années",
            notes = "Cette méthode permet de chercher et renvoyer le montant de Commande de l'année encours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant des Commande par années / somme nulle")
    })
    BigDecimal sumTotaleOfCommandeByYear();

    @GetMapping(value = "/find-listOrder-by-status-pending", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes dont le status encours",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes dont le status est encours", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getListOrderByStatusPending();

    @GetMapping(value = "/find-listOrder-by-status-payed", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes payees",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes payees", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes payees / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getListOrderByStatusPayed();

    @GetMapping(value = "/search-commande-by-userId-order-by-IdDesc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes par user",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes par user", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes par user / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getCommandesByUserOrderByIdDesc(@PathVariable(name = "id") Long id);

    @GetMapping(value = "/search-commande-by-billing-addressIdDesc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes par user",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes par user", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes par user / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getCommandesByBillingAddressOrderByIdDesc(@PathVariable(name = "id") Long id);

    @GetMapping(value = "/search-commande-by-shipping-addressIdDesc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes par user",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes par user", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes par user / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getCommandesByShippingAddressByIdDesc(@PathVariable(name = "id") Long id);

    @GetMapping(value = "/number-of-commande-by-day", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste du nombre de Commandes par jour",
            notes = "Cette méthode permet de chercher et renvoyer la liste du nombre de Commandes par jour", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste du nombre de Commandes par jour / une liste vide")
    })
    List<?> countNumberOfCommandeByDay();

    @GetMapping(value = "/number-of-commande-by-month", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste du nombre de Commandes par moi",
            notes = "Cette méthode permet de chercher et renvoyer la liste du nombre de Commandes par moi", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste du nombre de Commandes par moi / une liste vide")
    })
    List<?> countNumberOfCommandeByMonth();

    @GetMapping(value = "/sum-totale-of-commande-by-month-by-list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des sommes des Commandes par moi",
            notes = "Cette méthode permet de chercher et renvoyer liste des somme des Commandes par moi", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des sommes des Commandes par moi / une liste vide")
    })
    List<?> getSumTotaleOfCommandeByMonth();


    @GetMapping(value = "/sum-totale-of-commande-by-year-list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des sommes des Commandes par années",
            notes = "Cette méthode permet de chercher et renvoyer liste des somme des Commandes par années", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des sommes des Commandes par années / une liste vide")
    })
    List<?> getSumTotalOfOrdersByYears();

    @GetMapping(value = "/search-commandes-by-userId-by-pageable", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commande par client",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commande par Client par pages", responseContainer = "Page<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commande par Client par pages / une liste vide")
    })
    Page<CommandeDto> getCommandesByUtilisateurIdByPageables(@RequestParam("userId") Long userId, @RequestParam(name = "page") int page,
                                                             @RequestParam(name = "size") int size);

    @GetMapping(value = "/search-all-active-commandes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des commandes actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des commandes actives",
            responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commandes par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getAllActiveCommandes();

    @DeleteMapping(value = "/delete-commande/{idCommande}")
    @ApiOperation(value = "Supprimer une commandes par son ID",
            notes = "Cette méthode permet de supprimer une commandes par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commandes a été supprimé")
    })
    void deleteCommande(@PathVariable("idCommande") Long idCommande);
}
