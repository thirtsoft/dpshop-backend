package com.dp.dpshopbackend.security.service;

import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtilisateurRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur utilisateur = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        return UserPrinciple.build(utilisateur);
//               return UserPrinciple.build(UtilisateurPOSTDto.fromEntityToDto(utilisateur));
    }

/*
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur utilisateur = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + email));

        return UserPrinciple.build(utilisateur);
    }
    */

}
