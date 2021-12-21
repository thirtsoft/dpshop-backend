package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.BlogDto;
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

public interface BlogApi {

    @PostMapping(value = APP_ROOT + "/blogs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Blog",
            notes = "Cette méthode permet d'ajouter un Blog", response = BlogDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Blog a été crée"),
            @ApiResponse(code = 400, message = "Aucun Blog  crée / modifié")

    })
    ResponseEntity<BlogDto> save(@RequestBody BlogDto blogDto);

    @PostMapping(value = APP_ROOT + "/blogs/createBlogWithFile")
    @ApiOperation(value = "Enregistrer un Blog avec une photo",
            notes = "Cette méthode permet d'ajouter un Blog une photo", response = BlogDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Blog a été crée"),
            @ApiResponse(code = 400, message = "Aucun Blog  crée / modifié")

    })
    ResponseEntity<BlogDto> saveBlogWithFile(@RequestParam(name = "Blog") String blogDto,
                                             @RequestParam(name = "photoBlog") MultipartFile photoBlog) throws IOException;

    @PutMapping(value = APP_ROOT + "/blogs/update/{idBlog}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Blog par son ID",
            notes = "Cette méthode permet de modifier un Blog par son ID", response = BlogDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Blog a été modifiée"),
            @ApiResponse(code = 400, message = "Le Blog a n'est pas modifiée")
    })
    ResponseEntity<BlogDto> update(@PathVariable("idBlog") Long id, @RequestBody BlogDto blogDto);

    @GetMapping(value = APP_ROOT + "/blogs/findById/{idBlog}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Blog par ID",
            notes = "Cette méthode permet de chercher un Blog par son ID", response = BlogDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Blog a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Blog n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<BlogDto> findById(@PathVariable("idBlog") Long id);

    @GetMapping(value = APP_ROOT + "/blogs/searchbyTitle/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Blog par titre",
            notes = "Cette méthode permet de chercher son title par", response = BlogDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Blog a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Blog n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<BlogDto> findByTitle(@PathVariable("title") String title);

    @GetMapping(value = APP_ROOT + "/blogs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Blogs",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Blogs", responseContainer = "List<BlogDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Blogs / une liste vide")
    })
    ResponseEntity<List<BlogDto>> findAll();

    @GetMapping(value = APP_ROOT + "/blogs/searchTop5BlogOrderByCreatedDateDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 5 derniers Blogs enregistrées",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 5 derniers Blogs enregistrés", responseContainer = "List<BlogDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Blogs selectionner / une liste vide")
    })
    ResponseEntity<List<BlogDto>> getTop5ByOrderByCreateDateDesc();

    @GetMapping(value = APP_ROOT + "/blogs/searchAllBlogOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Blogs par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Blogs par ordre descroissante",
            responseContainer = "List<BlogDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Blogs  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<BlogDto>> getAllBlogsOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/blogs/photoBlog/{idBlog}")
    @ApiOperation(value = "Recupérer une photo par ID",
            notes = "Cette méthode permet de récupérer la photo d'un blog par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo est affiché")
    })
    byte[] getPhotoBlogle(@PathVariable("idBlog") Long id) throws Exception;

    @PostMapping(path = APP_ROOT + "/blogs/uploadBlogPhoto/{id}")
    @ApiOperation(value = "Enregistrer une photo dans un dossier",
            notes = "Cette méthode permet d'enregistrer la photo d'un blog dans un dossier externe utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été enregistré dans le dossier utilisateur")

    })
    void uploadPhotoBlog(MultipartFile file,
                         @PathVariable("id") Long id) throws IOException;

    @DeleteMapping(value = APP_ROOT + "/blogs/delete/{idBlog}")
    @ApiOperation(value = "Supprimer un Blog par son ID",
            notes = "Cette méthode permet de supprimer une Blog par son ID", response = BlogDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Blog a été supprimé")
    })
    void delete(@PathVariable("idBlog") Long id);

}
