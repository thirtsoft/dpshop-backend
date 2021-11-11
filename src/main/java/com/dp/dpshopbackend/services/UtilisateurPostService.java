package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.StateDto;
import com.dp.dpshopbackend.dto.UtilisateurPOSTDto;
import com.dp.dpshopbackend.message.request.LoginForm;
import com.dp.dpshopbackend.message.request.SignUpForm;

import java.util.List;

public interface UtilisateurPostService {

    UtilisateurPOSTDto registerUser(SignUpForm signUpRequest);

    UtilisateurPOSTDto authenticateUser(LoginForm loginRequest);

}
