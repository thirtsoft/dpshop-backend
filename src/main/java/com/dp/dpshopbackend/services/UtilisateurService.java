package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto findById(Long id);

    UtilisateurDto findByUsername(String username);

    List<UtilisateurDto> findAll();

    void delete(Long id);
}
