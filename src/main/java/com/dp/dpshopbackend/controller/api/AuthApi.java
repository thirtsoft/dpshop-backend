package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.UtilisateurPOSTDto;
import com.dp.dpshopbackend.message.request.LoginForm;
import com.dp.dpshopbackend.message.request.SignUpForm;
import com.dp.dpshopbackend.models.Utilisateur;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface AuthApi {
    /*
        @PostMapping(value = APP_ROOT + "/auth/signIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "S'authentifier",
                notes = "Cette méthode permet à un utilisateur de s'authentifier", response = UtilisateurPOSTDto.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "L'utilisateur a été authentifié"),
                @ApiResponse(code = 400, message = "Aucun Utilisateur avec ces paramètres")
        })
        ResponseEntity<UtilisateurPOSTDto> signIn(@RequestBody UtilisateurPOSTDto utilisateurPOSTDto);

        */
    @PostMapping(value = APP_ROOT + "/auth/authenticated", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "S'authentifier",
            notes = "Cette méthode permet à un utilisateur de s'authentifier", response = LoginForm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a été authentifié"),
            @ApiResponse(code = 400, message = "Aucun Utilisateur avec ces paramètres")
    })
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm);


    @PostMapping(value = APP_ROOT + "/auth/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Account",
            notes = "Cette méthode permet à un utilisateur de créer un compte une Category", response = SignUpForm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le compte a été crée"),
            @ApiResponse(code = 400, message = "Aucun Compte  crée / modifié")
    })
    ResponseEntity<?> signUp(@Valid @RequestBody SignUpForm signUpForm);


    @PostMapping(value = APP_ROOT + "/auth/registerUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Account",
            notes = "Cette méthode permet à un utilisateur de créer un compte une Category", response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le compte a été crée"),
            @ApiResponse(code = 400, message = "Aucun Compte  crée / modifié")
    })
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpForm);


}
