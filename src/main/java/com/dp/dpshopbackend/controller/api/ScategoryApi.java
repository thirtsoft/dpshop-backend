package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.ScategoryDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface ScategoryApi {

    @PostMapping(value = APP_ROOT + "/scategories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Scategory",
            notes = "Cette méthode permet d'ajouter une Scategory", response = ScategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Scategory a été crée"),
            @ApiResponse(code = 400, message = "Aucun Scategory  crée / modifié")
    })
    ResponseEntity<ScategoryDto> save(@RequestBody ScategoryDto scategoryDto);

    @PutMapping(value = APP_ROOT + "/scategories/update/{idScategory}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Scategory par son ID",
            notes = "Cette méthode permet de modifier une Scategory par son ID", response = ScategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Scategory a été modifiée"),
            @ApiResponse(code = 400, message = "La Scategory a n'est pas modifiée")
    })
    ResponseEntity<ScategoryDto> update(@PathVariable("idScategory") Long id, @RequestBody ScategoryDto scategoryDto);


    @GetMapping(value = APP_ROOT + "/scategories/{idScategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Scategory par ID",
            notes = "Cette méthode permet de chercher une Scategory par son ID", response = ScategoryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Scategory a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Scategory n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<ScategoryDto> findById(@PathVariable("idScategory") Long id);

    @GetMapping(value = APP_ROOT + "/scategories/searchbyLibelle/{libelle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Scategory par Libelle",
            notes = "Cette méthode permet de chercher une Scategory par son Libelle", response = ScategoryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Scategory a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Scategory n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<ScategoryDto> findByLibelle(@PathVariable("libelle") String libelle);

    @GetMapping(value = APP_ROOT + "/scategories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Scategories",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Scategories", responseContainer = "List<ScategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Scategories / une liste vide")
    })
    List<ScategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/scategories/delete/{idScategory}")
    @ApiOperation(value = "Supprimer un Scategory par son ID",
            notes = "Cette méthode permet de supprimer une Scategory par son ID", response = ScategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Scategory a été supprimé")
    })
    void delete(@PathVariable("idScategory") Long id);
}
