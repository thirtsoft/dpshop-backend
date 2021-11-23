package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto update(Long id, UtilisateurDto utilisateurDto);

    UtilisateurDto findById(Long id);

    UtilisateurDto findByUsername(String username);

    boolean updateUsernameOfUtilisateurByUsername(String username, String newUsername);

    boolean updateUsernameOfUtilisateurByUserId(String id, String newUsername);

    boolean updateCustomerPasswordByUsername(String username, String oldPass, String newPass);

    boolean updateCustomerPasswordByUserId(String id, String oldPass, String newPass);

    boolean updateCustomerProfileByUsername(String username, String name, String newUsername, String email, String mobile);

    List<UtilisateurDto> findByOrderByIdDesc();

    List<UtilisateurDto> findAll();

    void delete(Long id);
}
