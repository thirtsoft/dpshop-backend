package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.ArticleDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Artilce",
            notes = "Cette méthode permet d'ajouter un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Artilce a été crée"),
            @ApiResponse(code = 400, message = "Aucun Artilce  crée / modifié")

    })
    ResponseEntity<ArticleDto> save(@RequestBody ArticleDto articleDto);

    @PostMapping(value = APP_ROOT + "/articles/createWithFile")
    @ApiOperation(value = "Enregistrer un Artilce avec une photo",
            notes = "Cette méthode permet d'ajouter un article une photo", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Artilce a été crée"),
            @ApiResponse(code = 400, message = "Aucun Artilce  crée / modifié")

    })
    ResponseEntity<ArticleDto> saveArticleWithFile(@RequestPart(name = "article") String articleDto,
                                                   @RequestParam(name = "photoArticle") MultipartFile photoArticle) throws IOException;

    @PutMapping(value = APP_ROOT + "/articles/update/{idArticle}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un article par son ID",
            notes = "Cette méthode permet de modifier un article par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Artilce a été modifiée"),
            @ApiResponse(code = 400, message = "L'Artilce a n'est pas modifiée")
    })
    ResponseEntity<ArticleDto> update(@PathVariable("idArticle") Long id, @RequestBody ArticleDto articleDto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Artilce par ID",
            notes = "Cette méthode permet de chercher un Artilce par son ID", response = ArticleDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Artilce a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Artilce n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<ArticleDto> findById(@PathVariable("idArticle") Long id);

    @GetMapping(value = APP_ROOT + "/articles/searchbyReference/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Artilce par Reference",
            notes = "Cette méthode permet de chercher une Scategory par son Reference", response = ArticleDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Artilce a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Artilce n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<ArticleDto> findByReference(@PathVariable("reference") String reference);

    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles / une liste vide")
    })
    List<ArticleDto> findAll();

    @GetMapping(value = APP_ROOT + "/articles/articlesByScategories/{scatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par Scategory",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par Scategory", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles par Scategory / une liste vide")
    })
    List<ArticleDto> findListArticleByScategories(@PathVariable("scatId") Long idScategory);

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")
    @ApiOperation(value = "Supprimer un Article par son ID",
            notes = "Cette méthode permet de supprimer une Article par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Article a été supprimé")
    })
    void delete(@PathVariable("idArticle") Long id);

    @GetMapping(value = APP_ROOT + "/articles/photoArticle/{idArticle}")
    @ApiOperation(value = "Recupérer une photo par ID",
            notes = "Cette méthode permet de récupérer la photo d'un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo est affiché")
    })
    byte[] getPhotoArticle(@PathVariable("idArticle") Long id) throws Exception;

}
