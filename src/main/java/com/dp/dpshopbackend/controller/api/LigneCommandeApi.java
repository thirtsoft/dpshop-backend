package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.dto.LigneCommandeDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface LigneCommandeApi {

    @PostMapping(value = APP_ROOT + "/lignecommandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCommandeDto> save(@RequestBody LigneCommandeDto ligneCommandeDto);

    @GetMapping(value = APP_ROOT + "/lignecommandes/{idLignecommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCommandeDto> findById(@PathVariable("idLignecommande") Long id);

    @GetMapping(value = APP_ROOT + "/lignecommandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<LigneCommandeDto> findAll();

    @GetMapping(value = APP_ROOT + "/articles/searchAllLigneCommandeOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par ordre descroissante",
            responseContainer = "List<LigneCommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<LigneCommandeDto> > getAllLigneCommandeOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/lignecommandes/findListArticleGroupByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    List<LigneCommandeDto> getArticlesGroupByProductIdOrderByCreatedDateDesc();

    @DeleteMapping(value = APP_ROOT + "/lignecommandes/delete/{idLignecommande}")
    void delete(@PathVariable("idLignecommande") Long id);
}
