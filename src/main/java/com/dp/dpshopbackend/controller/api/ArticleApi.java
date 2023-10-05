package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.ArticleDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/articles")
public interface ArticleApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Article",
            notes = "Cette méthode permet d'ajouter un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Article a été crée"),
            @ApiResponse(code = 400, message = "Aucun Article  crée / modifié")

    })
    ResponseEntity<ArticleDto> save(@RequestBody ArticleDto articleDto);

    @PostMapping(value = "/create-with-file")
    @ApiOperation(value = "Enregistrer un Article avec une photo",
            notes = "Cette méthode permet d'ajouter un article une photo", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Article a été crée"),
            @ApiResponse(code = 400, message = "Aucun Artilce  crée / modifié")
    })
    ResponseEntity<ArticleDto> saveArticleWithFile(@RequestParam(name = "article") String articleDto,
                                                   @RequestParam(name = "photoArticle") MultipartFile photoArticle) throws IOException;

    @PostMapping(value = "/create-with-file-in-folder")
    @ApiOperation(value = "Enregistrer un Artilce avec une photo dans le dossier webapps",
            notes = "Cette méthode permet d'ajouter un article une photo dans le dossier interne webapps", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'Artilce a été crée"),
            @ApiResponse(code = 400, message = "Aucun Artilce  crée / modifié")

    })
    ResponseEntity<ArticleDto> saveArticleWithFileInFolder(@RequestParam(name = "article") String articleDto,
                                                           @RequestParam(name = "photoArticle") MultipartFile photoArticle) throws IOException;

    @PutMapping(value = "/update/{idArticle}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un article par son ID",
            notes = "Cette méthode permet de modifier un article par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Artilce a été modifiée"),
            @ApiResponse(code = 400, message = "L'Artilce a n'est pas modifiée")
    })
    ResponseEntity<ArticleDto> update(@PathVariable("idArticle") Long id, @RequestBody ArticleDto articleDto);

    @GetMapping(value = "/findById/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Artilce par ID",
            notes = "Cette méthode permet de chercher un Artilce par son ID", response = ArticleDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Artilce a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Artilce n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<ArticleDto> findById(@PathVariable("idArticle") Long id);

    @GetMapping(value = "/search-by-reference/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Artilce par Reference",
            notes = "Cette méthode permet de chercher une Scategory par son Reference", response = ArticleDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Article a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Article n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<ArticleDto> findByReference(@PathVariable("reference") String reference);

    @GetMapping(value = "/articles-by-subcategories/{scatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par Scategory",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par Scategory", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles par Scategory / une liste vide")
    })
    List<ArticleDto> findListArticleByScategories(@PathVariable("scatId") Long idScategory);

    @GetMapping(value = "/search-article-by-keyword", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par mot Clé",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par mot Clé", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles par Scategory / une liste vide")
    })
    List<ArticleDto> getListArticleByKeyword(@RequestParam(name = "keyword") String keyword);


    @GetMapping(value = "/search-article-by-price/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par price",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par price", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles par price / une liste vide")
    })
    List<ArticleDto> getListArticleByPrice(@PathVariable("price") double price);

    @GetMapping(value = "/search-article-by-price-min-max/{min}/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par price",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par price", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles par price / une liste vide")
    })
    List<ArticleDto> getListArticleByPriceMinMax(@PathVariable("min") String min, @PathVariable("max") String max);

    @GetMapping(value = "/search-articles-by-selected-is-true", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles selectionner",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles selectionner", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles selectionner / une liste vide")
    })
    List<ArticleDto> getListArticleBySelected();

    @GetMapping(value = "/search-top12-article-order-by-createdDate-desc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 12 derniers Articles enregistrées",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 12 derniers Articles enregistrés", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles selectionner / une liste vide")
    })
    List<ArticleDto> getTop12ByOrderByCreateDateDesc();

    @GetMapping(value = "/search-all-active-articles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles actives ordonnées par la designation",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles actives ordonnées par la designation",
            responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<ArticleDto>> getAllActiveArticlesOrderByDesignation();

    /*
    @GetMapping(value = "/search-all-by-actif-true-and-subcategory", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles actives ordonnées par la designation",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles actives ordonnées par la designation",
            responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<ArticleDto>> getAllArticlesByActifTrueAndSubcategoryId();*/

    @GetMapping(value = "/search-article-by-pageable", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par pages",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par pages", responseContainer = "Page<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles par pages / une liste vide")
    })
    Page<ArticleDto> getListArticleByPageable(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size);

    @GetMapping(value = "/search-article-by-subcategory-by-pageable", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par Scategory",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par Scategory par pages", responseContainer = "Page<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles par Scategory par pages / une liste vide")
    })
    Page<ArticleDto> getListArticleByScategoryByPageable(@RequestParam("id") Long scatId, @RequestParam(name = "page") int page,
                                                         @RequestParam(name = "size") int size);

    @GetMapping(value = "/search-article-by-same-price-by-pageable", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par price",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles qui ont le meme price par pages", responseContainer = "Page<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles par price par pages / une liste vide")
    })
    Page<ArticleDto> getListArticleBySamePriceyByPageable(@RequestParam("price") double price, @RequestParam(name = "page") int page,
                                                          @RequestParam(name = "size") int size);

    @GetMapping(value = "/count-number-of-article-in-subcategory/{subCatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre d'article pour une sous categorie",
            notes = "Cette méthode permet de chercher et renvoyer le nombre d'article dans une sous categorie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'article / le nombre est nulle")
    })
    BigDecimal countNumberOfArticleInSubCategory(@PathVariable("subCatId") Long subCatId);

    @GetMapping(value = "/photo-article/{idArticle}")
    @ApiOperation(value = "Recupérer une photo par ID",
            notes = "Cette méthode permet de récupérer la photo d'un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo est affiché")
    })
    byte[] getPhotoArticle(@PathVariable("idArticle") Long id) throws Exception;

    @GetMapping(value = "/photo-article-in-context/{idArticle}")
    @ApiOperation(value = "Recupérer une photo par ID",
            notes = "Cette méthode permet de récupérer la photo d'un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo est affiché")
    })
    byte[] getPhotoArticleInContext(@PathVariable("idArticle") Long id) throws Exception;

    @PostMapping(path = "/upload-photo-article/{id}")
    @ApiOperation(value = "Enregistrer une photo dans un dossier",
            notes = "Cette méthode permet d'enregistrer la photo d'un article dans un dossier externe utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été enregistré dans le dossier utilisateur")

    })
    void uploadPhotoArticle(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @PostMapping(path = "/upload-photo-article-in-folder/{id}")
    @ApiOperation(value = "Enregistrer une photo dans le dossier webapps",
            notes = "Cette méthode permet d'enregistrer la photo d'un article dans le dossier webapps")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été enregistré dans le dossier webapps")

    })
    void uploadPhotoArticleInFolder(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @DeleteMapping(value = "/delete-article/{idArticle}")
    @ApiOperation(value = "Supprimer un Article par son ID",
            notes = "Cette méthode permet de supprimer une Article par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Article a été supprimé")
    })
    void deleteArticle(@PathVariable("idArticle") Long id);


}
