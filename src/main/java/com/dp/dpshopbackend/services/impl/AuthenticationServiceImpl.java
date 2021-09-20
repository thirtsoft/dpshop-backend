package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.config.MessageStrings;
import com.dp.dpshopbackend.exceptions.AuthenticationFailException;
import com.dp.dpshopbackend.models.ConfirmToken;
import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.repository.ConfirmTokenRepository;
import com.dp.dpshopbackend.services.AuthenticationService;
import com.dp.dpshopbackend.utils.Helper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ConfirmTokenRepository confirmTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmToken confirmToken) {
        confirmTokenRepository.save(confirmToken);
    }

    @Override
    public ConfirmToken getToken(Utilisateur utilisateur) {
        return confirmTokenRepository.findTokenByUtilisateur(utilisateur);
    }

    @Override
    public Utilisateur getUser(String token) {
        ConfirmToken confirmToken = confirmTokenRepository.findTokenByToken(token);
        if (Helper.notNull(confirmToken)) {
            if (Helper.notNull(confirmToken.getUtilisateur())) {
                return confirmToken.getUtilisateur();
            }
        }
        return null;
    }

    @Override
    public void authenticate(String token) throws AuthenticationFailException {
        if (!Helper.notNull(token)) {
            throw  new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Helper.notNull(getUser(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }

    }
}
