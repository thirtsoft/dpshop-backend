package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.StateDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/states")
public interface StateApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un State",
            notes = "Cette méthode permet d'ajouter une State", response = StateDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La State a été crée"),
            @ApiResponse(code = 400, message = "Aucun State  crée / modifié")
    })
    ResponseEntity<StateDto> save(@RequestBody StateDto stateDto);

    @PutMapping(value = "/update/{idState}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une State par son ID",
            notes = "Cette méthode permet de modifier une State par son ID", response = StateDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La State a été modifiée"),
            @ApiResponse(code = 400, message = "La State a n'est pas modifiée")
    })
    ResponseEntity<StateDto> update(@PathVariable("idState") Long id, @RequestBody StateDto stateDto);

    @GetMapping(value = "/findById/{idState}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une State par ID",
            notes = "Cette méthode permet de chercher une State par son ID", response = StateDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La State a été trouver"),
            @ApiResponse(code = 404, message = "Aucun State n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<StateDto> findById(@PathVariable("idState") Long id);

    @GetMapping(value = "/search-state-by-country-code", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des states",
            notes = "Cette méthode permet de chercher et renvoyer la liste des states", responseContainer = "List<StateDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des states / une liste vide")
    })
    List<StateDto> getAllStateByCountryCode(@RequestParam(name = "code") String code);

    @GetMapping(value = "/search-all-active-states", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des states actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des states actives",
            responseContainer = "List<StateDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des states par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<StateDto>> getAllActiveStates();

    @DeleteMapping(value = "/delete-state/{idState}")
    @ApiOperation(value = "Supprimer une state par son ID",
            notes = "Cette méthode permet de supprimer une state par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La state a été supprimé")
    })
    void deleteState(@PathVariable("idState") Long idState);
}