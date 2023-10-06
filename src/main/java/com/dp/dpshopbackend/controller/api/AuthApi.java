package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.message.request.LoginForm;
import com.dp.dpshopbackend.message.request.SignUpForm;
import com.dp.dpshopbackend.models.Utilisateur;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/auth")
public interface AuthApi {

    @PostMapping(value = "/authenticated", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "S'authentifier",
            notes = "Cette méthode permet à un utilisateur de s'authentifier", response = LoginForm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a été authentifié"),
            @ApiResponse(code = 400, message = "Aucun Utilisateur avec ces paramètres")
    })
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm);


    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Account",
            notes = "Cette méthode permet à un utilisateur de créer un compte une Category", response = SignUpForm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le compte a été crée"),
            @ApiResponse(code = 400, message = "Aucun Compte  crée / modifié")
    })
    ResponseEntity<?> signUp(@Valid @RequestBody SignUpForm signUpForm);

    @PostMapping(value = "/registerUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Account",
            notes = "Cette méthode permet à un utilisateur de créer un compte une Category", response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le compte a été crée"),
            @ApiResponse(code = 400, message = "Aucun Compte  crée / modifié")
    })
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpForm);

    @GetMapping(value = "/currentUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Current User",
            notes = "Cette méthode permet de récuperer l'utilisateur courrant", response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'utilisateur est retourné"),
            @ApiResponse(code = 400, message = "Aucun Compte  crée / modifié")
    })
    String getcurrentUserName(Principal principal);

    @GetMapping(value = "/currentLogginUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Current User",
            notes = "Cette méthode permet de récuperer l'utilisateur courrant", response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'utilisateur est retourné"),
            @ApiResponse(code = 400, message = "Aucun Compte  crée / modifié")
    })
    String getcurrentUser();


}
