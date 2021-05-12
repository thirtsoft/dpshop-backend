package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto findById(Long id);

    //  ScategorieDto findByLibelle(String libelle);

    List<UtilisateurDto> findAll();

    void delete(Long id);
}
