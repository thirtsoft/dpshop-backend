package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.AuthApi;
import com.dp.dpshopbackend.dto.UtilisateurPOSTDto;
import com.dp.dpshopbackend.message.request.LoginForm;
import com.dp.dpshopbackend.message.request.SignUpForm;
import com.dp.dpshopbackend.services.UtilisateurPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
public class AuthController implements AuthApi {

    private final UtilisateurPostService utilisateurPostService;

    @Autowired
    public AuthController(UtilisateurPostService utilisateurPostService) {
        this.utilisateurPostService = utilisateurPostService;
    }


    @Override
    public ResponseEntity<UtilisateurPOSTDto> signIn(UtilisateurPOSTDto utilisateurPOSTDto) {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername(utilisateurPOSTDto.getUsername());
        loginForm.setPassword(loginForm.getPassword());

        UtilisateurPOSTDto utilisateurPOSTDtomResult = utilisateurPostService.authenticateUser(loginForm);

        if (utilisateurPOSTDtomResult != null) {
            log.info("User connected succefully");
            System.out.println("User connected good!");

        }else {
            log.info("User not connected succefully");
            System.out.println("User not connected good!");
        }

        return ResponseEntity.ok(utilisateurPOSTDtomResult);

    }

    @Override
    public ResponseEntity<UtilisateurPOSTDto> signUp(UtilisateurPOSTDto utilisateurPOSTDto) {
        SignUpForm signUpRequest = new SignUpForm();
        signUpRequest.setUsername(utilisateurPOSTDto.getUsername());
        signUpRequest.setEmail(utilisateurPOSTDto.getEmail());
        signUpRequest.setPassword(utilisateurPOSTDto.getPassword());

        UtilisateurPOSTDto utilisateurPOSTDtomResult = utilisateurPostService.registerUser(signUpRequest);

        if (utilisateurPOSTDtomResult != null) {
            log.info("User created succefully");
            System.out.println("User created good!");

        }else {
            log.info("User not created succefully");
            System.out.println("User not created good!");
        }

        return ResponseEntity.ok(utilisateurPOSTDtomResult);
    }
}
