package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.LigneCommandeDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/lignecommandes")
public interface LigneCommandeApi {

    @GetMapping(value =  "/findById/{idLignecommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCommandeDto> findById(@PathVariable("idLignecommande") Long id);

    @GetMapping(value = "/find-list-article-group-by-IdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCommandes par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneCommandes par ordre descroissante",
            responseContainer = "List<LigneCommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles  par ordre descroissante / une liste vide")
    })
    List<LigneCommandeDto> getArticlesGroupByProductIdOrderByCreatedDateDesc();

    @GetMapping(value = "/search-all-lignecommandes-by-commandeId/{comId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCommandes  par commande ID",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneCommandes par  commande ID",
            responseContainer = "List<LigneCommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCommandes  par commande ID / une liste vide")
    })
    ResponseEntity<List<LigneCommandeDto>> getAllLigneCommandesByCommandeId(@PathVariable("comId") Long comId);

    @GetMapping(value = "/search-top-lignecommandes-order-by-IdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 200 dernières LigneCommandes  par ordre décroissant",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 200 derniers LigneCommandes par ID décroissant",
            responseContainer = "List<LigneCommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des 200 dernières LigneCommandes  par ID décroissant / une liste vide")
    })
    ResponseEntity<List<LigneCommandeDto>> getTop200LigneCommandesOrderByIdDesc();

    @GetMapping(value = "/search-all-active-lignecommandes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des lignecommandes actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des lignecommandes actives",
            responseContainer = "List<LigneCommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des lignecommandes par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<LigneCommandeDto>> getAllActiveLigneCommandes();

}
