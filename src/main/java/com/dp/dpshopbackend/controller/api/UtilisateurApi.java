package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RequestMapping(value = APP_ROOT + "/utilisateurs")
public interface UtilisateurApi {

    @GetMapping(value = "/findById/{idUtilisateur}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDto> findById(@PathVariable("idUtilisateur") Long id);

    @GetMapping(value = "/search-utilisateur-by-username", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une utilisateur par son username",
            notes = "Cette méthode permet de chercher un utilisateur par son nom d'utilisateur", response = UtilisateurDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a été trouver avec cet identifiant fourni"),
            @ApiResponse(code = 404, message = "Aucun Utilisateur n'existe avec ce username pas dans la BD")

    })
    ResponseEntity<UtilisateurDto> getUtilisateurByUsername(@RequestParam(value = "username") String username);

    @GetMapping(value = "/avatar/{id}")
    @ApiOperation(value = "Recupérer une photo par ID",
            notes = "Cette méthode permet de chercher et d'afficher la photo d'un Utilisateur par son ID"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo de l'utilisateur a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Photo n'existe avec cette ID pas dans la BD")

    })
    byte[] getPhoto(@PathVariable("id") Long id) throws Exception;

    @PostMapping(value = "/upload-photo-to-user/{id}", produces = IMAGE_PNG_VALUE)
    @ApiOperation(value = "Enregistrer une photo dans un dossier",
            notes = "Cette méthode permet d'enregistrer la photo d'un utilisateur dans un dossier externe utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été enregistré dans le dossier utilisateur")
    })
    void uploadUserPhoto(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @PutMapping(value = "/update/{idUser}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un utilisateur par son ID",
            notes = "Cette méthode permet de modifier un utilisateur par son ID", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a été été modifié"),
            @ApiResponse(code = 400, message = "Aucun utiisateur n'a été modifié")
    })
    ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable(value = "idUser") Long idUser, @RequestBody UtilisateurDto utilisateur);

    @PatchMapping(value = "/update-username-of-user-by-username")
    @ApiOperation(value = "Modifier le username par son username",
            notes = "Cette méthode permet de modifier le nom d'utilisateur d'un utilisateur par son username", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nom d'utlisateur a été été modifié"),
            @ApiResponse(code = 400, message = "Aucun username n'a été modifié")
    })
    ResponseEntity<Boolean> updateUsername(@RequestBody ObjectNode json);

    @PatchMapping(value = "/update-username-of-user-byId")
    @ApiOperation(value = "Modifier le username par son ID",
            notes = "Cette méthode permet de modifier le nom d'utilisateur d'un utilisateur par son ID", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nom d'utlisateur a été été modifié"),
            @ApiResponse(code = 400, message = "Aucun username n'a été modifié")
    })
    ResponseEntity<Boolean> updateUsernameByUserId(@RequestBody ObjectNode json);

    @PatchMapping(value = "/update-password-by-username")
    @ApiOperation(value = "Modifier le mot de passe par son Username ",
            notes = "Cette méthode permet de modifier le mot de passe d'un utilisateur par son Username", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mot de passe a été été modifié"),
            @ApiResponse(code = 400, message = "Aucun mot de passe n'a été modifié avec ce username")
    })
    ResponseEntity<Boolean> updatePasswordByUsername(@RequestBody ObjectNode json);

    @PatchMapping(value = "/update-password-by-userId")
    @ApiOperation(value = "Modifier le mot de passe par son ID ",
            notes = "Cette méthode permet de modifier le mot de passe d'un utilisateur par son ID", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mot de passe a été été modifié"),
            @ApiResponse(code = 400, message = "Aucun mot de passe n'a été modifié avec ce username")
    })
    ResponseEntity<Boolean> updatePasswordByUserId(@RequestBody ObjectNode json);

    @PatchMapping(value = "/update-customer-profile-by-username")
    @ApiOperation(value = "Modifier le mot de passe par son username ",
            notes = "Cette méthode permet de modifier le mot de passe d'un utilisateur par son username", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mot de passe a été été modifié"),
            @ApiResponse(code = 400, message = "Aucun mot de passe n'a été modifié avec ce username")
    })
    ResponseEntity<Boolean> updateCustomerProfileByUsername(@RequestBody ObjectNode json);

    @GetMapping(value = "/search-all-active-utilisateurs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des utilisateurs actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des utilisateurs actives",
            responseContainer = "List<UtilisateurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des utilisateurs par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<UtilisateurDto>> getAllActiveUtilisateurs();

    @DeleteMapping(value = "/delete-utilisateur/{idUtilisateur}")
    @ApiOperation(value = "Supprimer une utilisateur par son ID",
            notes = "Cette méthode permet de supprimer une utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La utilisateur a été supprimé")
    })
    void deleteUtilisateur(@PathVariable("idUtilisateur") Long idUtilisateur);
}