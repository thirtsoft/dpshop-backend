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

@RequestMapping(value = APP_ROOT + "/scategories")
public interface ScategoryApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Scategory",
            notes = "Cette méthode permet d'ajouter une Scategory", response = ScategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Scategory a été crée"),
            @ApiResponse(code = 400, message = "Aucun Scategory  crée / modifié")
    })
    ResponseEntity<ScategoryDto> save(@RequestBody ScategoryDto scategoryDto);

    @PutMapping(value = "/update/{idScategory}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Scategory par son ID",
            notes = "Cette méthode permet de modifier une Scategory par son ID", response = ScategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Scategory a été modifiée"),
            @ApiResponse(code = 400, message = "La Scategory a n'est pas modifiée")
    })
    ResponseEntity<ScategoryDto> update(@PathVariable("idScategory") Long id, @RequestBody ScategoryDto scategoryDto);

    @GetMapping(value = "/findById/{idScategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Scategory par ID",
            notes = "Cette méthode permet de chercher une Scategory par son ID", response = ScategoryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Scategory a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Scategory n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<ScategoryDto> findById(@PathVariable("idScategory") Long id);

    @GetMapping(value = "/search-all-active-scategories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des scategories actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des scategories actives",
            responseContainer = "List<ScategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des scategories par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<ScategoryDto>> getAllActiveSubCategories();

    @DeleteMapping(value = "/delete-scategorie/{idScategory}")
    @ApiOperation(value = "Supprimer une scategorie par son ID",
            notes = "Cette méthode permet de supprimer une scategorie par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La scategorie a été supprimé")
    })
    void deleteSubCategory(@PathVariable("idScategory") Long idScategory);
}
