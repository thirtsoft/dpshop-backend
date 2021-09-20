package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.exceptions.AuthenticationFailException;
import com.dp.dpshopbackend.models.ConfirmToken;
import com.dp.dpshopbackend.models.Utilisateur;

public interface AuthenticationService {

    void saveConfirmationToken(ConfirmToken confirmToken);

    ConfirmToken getToken(Utilisateur utilisateur);

    Utilisateur getUser(String token);

    void authenticate(String token) throws AuthenticationFailException;

}
