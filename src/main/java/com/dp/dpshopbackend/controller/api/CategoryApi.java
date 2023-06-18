package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.CategoryDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Category",
            notes = "Cette méthode permet d'ajouter une Category", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Category a été crée"),
            @ApiResponse(code = 400, message = "Aucun Category  crée / modifié")
    })
    ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto);

    @PutMapping(value = APP_ROOT + "/categories/update/{idCategory}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Category par son ID",
            notes = "Cette méthode permet de modifier une Category par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Category a été modifiée"),
            @ApiResponse(code = 400, message = "La Category a n'est pas modifiée")
    })
    ResponseEntity<CategoryDto> update(@PathVariable("idCategory") Long id, @RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT + "/categories/findById/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Category par ID",
            notes = "Cette méthode permet de chercher une Category par son ID", response = CategoryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Category a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Category n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<CategoryDto> findById(@PathVariable("idCategory") Long id);

    @GetMapping(value = APP_ROOT + "/categories/searchbyDesignation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Category par Designation",
            notes = "Cette méthode permet de chercher une Category par son Libelle", response = CategoryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Category a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Category n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<CategoryDto> findByDesignation(@PathVariable("designation") String designation);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Categories",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Categories", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Categories / une liste vide")
    })
    List<CategoryDto> findAll();

    @GetMapping(value = APP_ROOT + "/categories/searchAllCategorieOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par ordre descroissante",
            responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<CategoryDto> > getAllCategoriesOrderByIdDesc();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    @ApiOperation(value = "Supprimer un Category par son ID",
            notes = "Cette méthode permet de supprimer une Category par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Category a été supprimé")
    })
    void delete(@PathVariable("idCategory") Long id);

    @GetMapping(value = APP_ROOT + "/categories/search-all-active-categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des categories actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des categories actives",
            responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des AddressLivraisons par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<CategoryDto>> getAllActiveCategories();

    @DeleteMapping(value = APP_ROOT + "/categories/delete-categories/{idCategory}")
    @ApiOperation(value = "Supprimer une Category par son ID",
            notes = "Cette méthode permet de supprimer une Category par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Categorie a été supprimé")
    })
    void deleteCategory(@PathVariable("idCategory") Long idCategory);
}
