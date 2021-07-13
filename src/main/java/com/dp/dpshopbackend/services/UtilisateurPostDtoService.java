package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.UtilisateurPOSTDto;
import com.dp.dpshopbackend.message.request.LoginForm;
import com.dp.dpshopbackend.message.request.SignUpForm;

import java.util.List;

public interface UtilisateurPostDtoService {

    UtilisateurPOSTDto save(UtilisateurPOSTDto utilisateurPOSTDto);

    UtilisateurPOSTDto update(Long id, UtilisateurPOSTDto utilisateurPOSTDto);

    boolean updateUsernameOfUtilisateur(String username, String newUsername);

    boolean updatePasswordofUtilisateur(String username, String oldPass, String newPass);

    UtilisateurPOSTDto findById(Long id);

    UtilisateurPOSTDto findUtilisateurByUsername(String username);

    List<UtilisateurPOSTDto> findAll();

    void delete(Long id);

    UtilisateurPOSTDto registerUser(SignUpForm signUpRequest);

    UtilisateurPOSTDto authenticateUser(LoginForm loginRequest);

}
