package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.*;
import com.dp.dpshopbackend.enumeration.RoleName;
import com.dp.dpshopbackend.exceptions.AuthenticationFailException;
import com.dp.dpshopbackend.exceptions.CustomException;
import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.repository.UtilisateurRepository;
import com.dp.dpshopbackend.services.AuthenticationService;
import com.dp.dpshopbackend.services.UserService;
import com.dp.dpshopbackend.utils.Helper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class UserServiceImpl {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private AuthenticationService authenticationService;

    //   Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

}
